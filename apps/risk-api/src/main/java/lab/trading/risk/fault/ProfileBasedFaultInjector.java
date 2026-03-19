package lab.trading.risk.fault;

import java.util.concurrent.ThreadLocalRandom;
import lab.trading.risk.domain.FaultProfile;
import lab.trading.risk.domain.FaultTargetMatcher;
import lab.trading.risk.domain.RiskContext;
import org.springframework.stereotype.Component;

@Component
public class ProfileBasedFaultInjector implements FaultInjector {

    private final InMemoryFaultProfileStore store;
    private final CpuBurner cpuBurner;

    public ProfileBasedFaultInjector(InMemoryFaultProfileStore store, CpuBurner cpuBurner) {
        this.store = store;
        this.cpuBurner = cpuBurner;
    }

    @Override
    public void beforeEvaluation(RiskContext context) {
        FaultProfile profile = store.get();
        if (!FaultTargetMatcher.matches(profile, context)) {
            return;
        }

        if (profile.fixedLatencyMs() > 0) {
            sleep(profile.fixedLatencyMs());
        }

        if (profile.cpuBurnMs() > 0) {
            cpuBurner.burn(profile.cpuBurnMs());
        }

        if (profile.errorRatePercent() > 0
                && ThreadLocalRandom.current().nextInt(100) < profile.errorRatePercent()) {
            throw new IllegalStateException("Injected risk-api failure");
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted during fault injection", e);
        }
    }
}
