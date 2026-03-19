package lab.trading.trade.messaging;

import io.micrometer.core.instrument.MeterRegistry;
import lab.trading.trade.domain.OrderAcceptedDomainEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderAcceptedKafkaRelay {

    private final OrderAcceptedEventPublisher publisher;
    private final OrderAcceptedEventMapper mapper;
    private final MeterRegistry meterRegistry;

    public OrderAcceptedKafkaRelay(
            OrderAcceptedEventPublisher publisher,
            OrderAcceptedEventMapper mapper,
            MeterRegistry meterRegistry
    ) {
        this.publisher = publisher;
        this.mapper = mapper;
        this.meterRegistry = meterRegistry;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void on(OrderAcceptedDomainEvent event) {
        try {
            publisher.publish(mapper.toExternal(event));
        } catch (Exception e) {
            meterRegistry.counter("order.accepted.publish.failure.total").increment();
            throw e;
        }
    }
}
