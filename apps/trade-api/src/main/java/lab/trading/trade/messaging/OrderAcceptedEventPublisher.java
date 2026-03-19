package lab.trading.trade.messaging;

import lab.trading.contracts.event.OrderAcceptedEvent;

public interface OrderAcceptedEventPublisher {

    void publish(OrderAcceptedEvent event);
}
