package lab.trading.trade.persistence;

import org.springframework.stereotype.Component;

@Component
public class OrderReader {

    private final OrderJpaRepository orderJpaRepository;

    public OrderReader(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    public OrderEntity findByIdOrThrow(String orderId) {
        return orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }
}
