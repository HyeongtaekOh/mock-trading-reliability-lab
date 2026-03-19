package lab.trading.risk.domain;

import java.util.Optional;

public interface RiskRule {

    Optional<RiskDecision> evaluate(RiskContext context);

    int order();
}
