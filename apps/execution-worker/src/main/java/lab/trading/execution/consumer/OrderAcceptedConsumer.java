package lab.trading.execution.consumer;

import lab.trading.execution.application.ExecutionProcessingService;
import org.springframework.stereotype.Component;

@Component
public class OrderAcceptedConsumer {

    private final ExecutionProcessingService executionProcessingService;

    public OrderAcceptedConsumer(ExecutionProcessingService executionProcessingService) {
        this.executionProcessingService = executionProcessingService;
    }

    public void consume(String orderId) {
        executionProcessingService.process(orderId);
    }
}
