package lab.trading.trade.domain;

import java.math.BigDecimal;

public record PortfolioPositionView(
        String symbol,
        Integer quantity,
        BigDecimal avgPrice
) {
}
