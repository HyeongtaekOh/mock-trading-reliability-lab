package lab.trading.risk.domain;

import java.math.BigDecimal;
import lab.trading.risk.dto.RiskCheckRequest;

public record RiskContext(
        Long userId,
        String symbol,
        String side,
        Integer quantity,
        BigDecimal price
) {

    public static RiskContext from(RiskCheckRequest request) {
        return new RiskContext(
                request.userId(),
                request.symbol(),
                request.side(),
                request.quantity(),
                request.price()
        );
    }

    public BigDecimal orderAmount() {
        return price.multiply(BigDecimal.valueOf(quantity.longValue()));
    }
}
