package lab.trading.risk.domain;

public record RiskDecision(
        String reasonCode,
        String reasonMessage
) {
}
