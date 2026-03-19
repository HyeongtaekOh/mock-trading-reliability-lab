package lab.trading.trade.messaging;

import lab.trading.contracts.event.OrderAcceptedEvent;
import lab.trading.contracts.kafka.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderAcceptedEventPublisher implements OrderAcceptedEventPublisher {

    private final KafkaTemplate<String, OrderAcceptedEvent> kafkaTemplate;

    public KafkaOrderAcceptedEventPublisher(KafkaTemplate<String, OrderAcceptedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(OrderAcceptedEvent event) {
        kafkaTemplate.send(Topics.ORDER_ACCEPTED_V1, event.orderId(), event);
    }
}
