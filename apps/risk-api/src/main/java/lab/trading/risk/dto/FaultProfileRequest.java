package lab.trading.risk.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lab.trading.risk.domain.FaultProfile;

public record FaultProfileRequest(
        @Min(0) long fixedLatencyMs,
        @Min(0) @Max(100) int errorRatePercent,
        @Min(0) long cpuBurnMs,
        String enabledForSymbol,
        Long enabledForUserId
) {

    public FaultProfile toProfile() {
        return new FaultProfile(fixedLatencyMs, errorRatePercent, cpuBurnMs, enabledForSymbol, enabledForUserId);
    }
}
