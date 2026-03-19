package lab.trading.risk.application;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lab.trading.risk.domain.RiskContext;
import lab.trading.risk.domain.RiskDecision;
import lab.trading.risk.domain.RiskRule;
import lab.trading.risk.dto.RiskCheckRequest;
import lab.trading.risk.dto.RiskCheckResponse;
import lab.trading.risk.fault.FaultInjector;
import org.springframework.stereotype.Service;

@Service
public class RiskCheckService {

    private final FaultInjector faultInjector;
    private final List<RiskRule> riskRules;
    private final RiskMetricsRecorder metricsRecorder;

    public RiskCheckService(
            FaultInjector faultInjector,
            List<RiskRule> riskRules,
            RiskMetricsRecorder metricsRecorder
    ) {
        this.faultInjector = faultInjector;
        this.riskRules = riskRules.stream().sorted(Comparator.comparingInt(RiskRule::order)).toList();
        this.metricsRecorder = metricsRecorder;
    }

    public RiskCheckResponse check(RiskCheckRequest request) {
        RiskContext context = RiskContext.from(request);

        return metricsRecorder.record(context, () -> {
            faultInjector.beforeEvaluation(context);

            for (RiskRule rule : riskRules) {
                Optional<RiskDecision> rejected = rule.evaluate(context);
                if (rejected.isPresent()) {
                    RiskDecision decision = rejected.get();
                    return RiskCheckResponse.reject(decision.reasonCode(), decision.reasonMessage());
                }
            }
            return RiskCheckResponse.approve();
        });
    }
}
