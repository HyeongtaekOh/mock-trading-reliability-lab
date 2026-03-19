package lab.trading.execution.application;

import org.springframework.stereotype.Service;

@Service
public class ExecutionProcessingService {

    private final PositionAggregationService positionAggregationService;

    public ExecutionProcessingService(PositionAggregationService positionAggregationService) {
        this.positionAggregationService = positionAggregationService;
    }

    public void process(String orderId) {
        positionAggregationService.refresh(orderId);
    }
}
