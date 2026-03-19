package lab.trading.trade.application;

import java.time.Instant;
import lab.trading.trade.domain.OrderAcceptedDomainEvent;
import lab.trading.trade.dto.CreateOrderRequest;
import lab.trading.trade.dto.CreateOrderResponse;
import lab.trading.trade.persistence.OrderEntity;
import lab.trading.trade.persistence.OrderJpaRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcceptedOrderWriter {

    private final OrderJpaRepository orderJpaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AcceptedOrderWriter(
            OrderJpaRepository orderJpaRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.orderJpaRepository = orderJpaRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public CreateOrderResponse write(CreateOrderRequest request) {
        OrderEntity entity = OrderEntity.accepted(request);
        orderJpaRepository.save(entity);

        eventPublisher.publishEvent(new OrderAcceptedDomainEvent(
                entity.getId(),
                entity.getUserId(),
                entity.getSymbol(),
                entity.getSide().name(),
                entity.getQuantity(),
                entity.getPrice(),
                Instant.now()
        ));

        return new CreateOrderResponse(entity.getId(), entity.getStatus().name());
    }
}
