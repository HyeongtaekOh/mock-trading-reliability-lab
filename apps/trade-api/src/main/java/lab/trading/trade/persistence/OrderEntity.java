package lab.trading.trade.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lab.trading.trade.domain.OrderStatus;
import lab.trading.trade.domain.Side;
import lab.trading.trade.dto.CreateOrderRequest;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private String id;

    private Long userId;

    private String symbol;

    @Enumerated(EnumType.STRING)
    private Side side;

    private Integer quantity;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String rejectionReason;

    private Instant createdAt;

    private Instant updatedAt;

    @Version
    private Long version;

    protected OrderEntity() {
    }

    public static OrderEntity accepted(CreateOrderRequest request) {
        OrderEntity entity = new OrderEntity();
        entity.id = UUID.randomUUID().toString();
        entity.userId = request.userId();
        entity.symbol = request.symbol();
        entity.side = Side.valueOf(request.side());
        entity.quantity = request.quantity();
        entity.price = request.price();
        entity.status = OrderStatus.ACCEPTED;
        entity.createdAt = Instant.now();
        entity.updatedAt = entity.createdAt;
        return entity;
    }

    public static OrderEntity rejected(CreateOrderRequest request, String reasonCode, String reasonMessage) {
        OrderEntity entity = accepted(request);
        entity.status = OrderStatus.RISK_REJECTED;
        entity.rejectionReason = reasonCode + ":" + reasonMessage;
        return entity;
    }

    public void markExecuting() {
        this.status = OrderStatus.EXECUTING;
        this.updatedAt = Instant.now();
    }

    public void markFilled() {
        this.status = OrderStatus.FILLED;
        this.updatedAt = Instant.now();
    }

    public void markFailed() {
        this.status = OrderStatus.FAILED;
        this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public Side getSide() {
        return side;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
