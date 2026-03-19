package lab.trading.contracts.event;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderAcceptedEvent(
        String eventId,
        Instant occurredAt,
        String orderId,
        Long userId,
        String symbol,
        String side,
        Integer quantity,
        BigDecimal price
) {
}
