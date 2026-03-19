package lab.trading.risk.fault;

import lab.trading.risk.domain.RiskContext;

public interface FaultInjector {

    void beforeEvaluation(RiskContext context);
}
