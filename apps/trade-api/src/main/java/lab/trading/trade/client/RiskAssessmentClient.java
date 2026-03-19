package lab.trading.trade.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lab.trading.trade.dto.CreateOrderRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RiskAssessmentClient implements RiskAssessmentGateway {

    private final RestClient restClient;

    public RiskAssessmentClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    @Retry(name = "riskCheck")
    @CircuitBreaker(name = "riskCheck")
    public RiskAssessmentResult assess(CreateOrderRequest request) {
        RiskCheckResponse response = restClient.post()
                .uri("/risk/check")
                .body(RiskCheckRequest.from(request))
                .retrieve()
                .body(RiskCheckResponse.class);

        if (response == null) {
            throw new IllegalStateException("risk-api returned null body");
        }

        return RiskAssessmentResult.from(response);
    }
}
