package lab.trading.risk.dto;

import lab.trading.risk.domain.FaultProfile;

public record FaultProfileResponse(
        long fixedLatencyMs,
        int errorRatePercent,
        long cpuBurnMs,
        String enabledForSymbol,
        Long enabledForUserId
) {

    public static FaultProfileResponse from(FaultProfile profile) {
        return new FaultProfileResponse(
                profile.fixedLatencyMs(),
                profile.errorRatePercent(),
                profile.cpuBurnMs(),
                profile.enabledForSymbol(),
                profile.enabledForUserId()
        );
    }
}
