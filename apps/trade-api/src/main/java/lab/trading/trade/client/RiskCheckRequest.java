package lab.trading.trade.client;

import java.math.BigDecimal;
import lab.trading.trade.dto.CreateOrderRequest;

public record RiskCheckRequest(
        Long userId,
        String symbol,
        String side,
        Integer quantity,
        BigDecimal price
) {

    public static RiskCheckRequest from(CreateOrderRequest request) {
        return new RiskCheckRequest(
                request.userId(),
                request.symbol(),
                request.side(),
                request.quantity(),
                request.price()
        );
    }
}
