package lab.trading.trade.client;

public record RiskAssessmentResult(
        boolean approved,
        String reasonCode,
        String reasonMessage
) {

    public static RiskAssessmentResult from(RiskCheckResponse response) {
        return new RiskAssessmentResult(response.approved(), response.reasonCode(), response.reasonMessage());
    }
}
