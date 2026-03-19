package lab.trading.trade.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderAcceptedDomainEvent(
        String orderId,
        Long userId,
        String symbol,
        String side,
        Integer quantity,
        BigDecimal price,
        Instant occurredAt
) {
}
