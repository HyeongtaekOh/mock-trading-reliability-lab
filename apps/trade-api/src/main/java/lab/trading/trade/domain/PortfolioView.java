package lab.trading.trade.domain;

import java.util.List;

public record PortfolioView(
        Long userId,
        List<PortfolioPositionView> positions
) {
}
