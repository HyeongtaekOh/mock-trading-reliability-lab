package lab.trading.trade.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lab.trading.trade.persistence.OrderEntity;

public record OrderDetailResponse(
        String orderId,
        Long userId,
        String symbol,
        String side,
        Integer quantity,
        BigDecimal price,
        String status,
        Instant createdAt,
        Instant updatedAt
) {

    public static OrderDetailResponse from(OrderEntity entity) {
        return new OrderDetailResponse(
                entity.getId(),
                entity.getUserId(),
                entity.getSymbol(),
                entity.getSide().name(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getStatus().name(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
