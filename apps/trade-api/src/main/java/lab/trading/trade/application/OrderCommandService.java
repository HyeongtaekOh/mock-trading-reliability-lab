package lab.trading.trade.application;

import lab.trading.trade.client.RiskAssessmentGateway;
import lab.trading.trade.client.RiskAssessmentResult;
import lab.trading.trade.dto.CreateOrderRequest;
import lab.trading.trade.dto.CreateOrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandService {

    private final RiskAssessmentGateway riskAssessmentGateway;
    private final AcceptedOrderWriter acceptedOrderWriter;
    private final RejectedOrderWriter rejectedOrderWriter;

    public OrderCommandService(
            RiskAssessmentGateway riskAssessmentGateway,
            AcceptedOrderWriter acceptedOrderWriter,
            RejectedOrderWriter rejectedOrderWriter
    ) {
        this.riskAssessmentGateway = riskAssessmentGateway;
        this.acceptedOrderWriter = acceptedOrderWriter;
        this.rejectedOrderWriter = rejectedOrderWriter;
    }

    public CreateOrderResponse placeOrder(CreateOrderRequest request) {
        RiskAssessmentResult result = riskAssessmentGateway.assess(request);
        if (!result.approved()) {
            return rejectedOrderWriter.write(request, result);
        }
        return acceptedOrderWriter.write(request);
    }
}
