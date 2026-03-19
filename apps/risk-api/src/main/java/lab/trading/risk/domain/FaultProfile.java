package lab.trading.risk.domain;

public record FaultProfile(
        long fixedLatencyMs,
        int errorRatePercent,
        long cpuBurnMs,
        String enabledForSymbol,
        Long enabledForUserId
) {

    public boolean appliesTo(RiskContext context) {
        boolean symbolMatched = enabledForSymbol == null || enabledForSymbol.isBlank()
                || enabledForSymbol.equalsIgnoreCase(context.symbol());
        boolean userMatched = enabledForUserId == null || enabledForUserId.equals(context.userId());
        return symbolMatched && userMatched;
    }
}
