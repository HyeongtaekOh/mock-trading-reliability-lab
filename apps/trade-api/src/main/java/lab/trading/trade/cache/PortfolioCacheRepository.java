package lab.trading.trade.cache;

import java.time.Duration;
import java.util.Optional;
import lab.trading.trade.domain.PortfolioView;

public interface PortfolioCacheRepository {

    Optional<PortfolioView> get(Long userId);

    void put(Long userId, PortfolioView view, Duration ttl);

    void evict(Long userId);
}
