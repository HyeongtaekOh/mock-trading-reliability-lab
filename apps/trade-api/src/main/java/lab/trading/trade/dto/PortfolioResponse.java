package lab.trading.trade.dto;

import java.math.BigDecimal;
import java.util.List;
import lab.trading.trade.domain.PortfolioPositionView;
import lab.trading.trade.domain.PortfolioView;

public record PortfolioResponse(
        Long userId,
        List<PositionPayload> positions,
        boolean cacheHit
) {

    public static PortfolioResponse from(PortfolioView view, boolean cacheHit) {
        List<PositionPayload> payloads = view.positions().stream()
                .map(position -> new PositionPayload(position.symbol(), position.quantity(), position.avgPrice()))
                .toList();
        return new PortfolioResponse(view.userId(), payloads, cacheHit);
    }

    public record PositionPayload(
            String symbol,
            Integer quantity,
            BigDecimal avgPrice
    ) {
    }
}
