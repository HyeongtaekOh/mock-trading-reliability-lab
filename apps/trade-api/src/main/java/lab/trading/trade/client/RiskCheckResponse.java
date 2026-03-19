package lab.trading.trade.client;

public record RiskCheckResponse(
        boolean approved,
        String reasonCode,
        String reasonMessage
) {
}
