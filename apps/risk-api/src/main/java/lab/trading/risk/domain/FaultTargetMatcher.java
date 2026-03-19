package lab.trading.risk.domain;

public final class FaultTargetMatcher {

    private FaultTargetMatcher() {
    }

    public static boolean matches(FaultProfile profile, RiskContext context) {
        return profile.appliesTo(context);
    }
}
