package lab.trading.trade.client;

import lab.trading.trade.dto.CreateOrderRequest;

public interface RiskAssessmentGateway {

    RiskAssessmentResult assess(CreateOrderRequest request);
}
