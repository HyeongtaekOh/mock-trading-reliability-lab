package lab.trading.trade.messaging;

import java.util.UUID;
import lab.trading.contracts.event.OrderAcceptedEvent;
import lab.trading.trade.domain.OrderAcceptedDomainEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderAcceptedEventMapper {

    public OrderAcceptedEvent toExternal(OrderAcceptedDomainEvent event) {
        return new OrderAcceptedEvent(
                UUID.randomUUID().toString(),
                event.occurredAt(),
                event.orderId(),
                event.userId(),
                event.symbol(),
                event.side(),
                event.quantity(),
                event.price()
        );
    }
}
