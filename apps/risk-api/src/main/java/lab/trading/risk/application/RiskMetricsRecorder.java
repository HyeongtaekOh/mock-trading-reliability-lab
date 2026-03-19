package lab.trading.risk.application;

import java.util.function.Supplier;
import lab.trading.risk.domain.RiskContext;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

@Component
public class RiskMetricsRecorder {

    private final MeterRegistry meterRegistry;

    public RiskMetricsRecorder(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public <T> T record(RiskContext context, Supplier<T> supplier) {
        Timer timer = Timer.builder("risk_check_duration")
                .tag("symbol", context.symbol())
                .register(meterRegistry);
        return timer.record(supplier);
    }
}
