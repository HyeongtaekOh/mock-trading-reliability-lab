package lab.trading.trade.application;

import java.time.Duration;
import java.util.List;
import lab.trading.trade.cache.PortfolioCacheRepository;
import lab.trading.trade.domain.PortfolioPositionView;
import lab.trading.trade.domain.PortfolioView;
import lab.trading.trade.dto.PortfolioResponse;
import lab.trading.trade.persistence.PositionEntity;
import lab.trading.trade.persistence.PositionJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PortfolioQueryService {

    private final PortfolioCacheRepository cacheRepository;
    private final PositionJpaRepository positionJpaRepository;

    public PortfolioQueryService(
            PortfolioCacheRepository cacheRepository,
            PositionJpaRepository positionJpaRepository
    ) {
        this.cacheRepository = cacheRepository;
        this.positionJpaRepository = positionJpaRepository;
    }

    public PortfolioResponse get(Long userId) {
        return cacheRepository.get(userId)
                .map(view -> PortfolioResponse.from(view, true))
                .orElseGet(() -> {
                    List<PortfolioPositionView> positions = positionJpaRepository.findByUserId(userId).stream()
                            .map(PositionEntity::toView)
                            .toList();
                    PortfolioView view = new PortfolioView(userId, positions);
                    cacheRepository.put(userId, view, Duration.ofSeconds(30));
                    return PortfolioResponse.from(view, false);
                });
    }
}
