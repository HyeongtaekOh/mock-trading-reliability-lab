package lab.trading.risk.rule;

import java.math.BigDecimal;
import java.util.Optional;
import lab.trading.risk.domain.RiskContext;
import lab.trading.risk.domain.RiskDecision;
import lab.trading.risk.domain.RiskRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MaxOrderAmountRule implements RiskRule {

    private final BigDecimal maxOrderAmount;

    public MaxOrderAmountRule(@Value("${app.rules.max-order-amount:1000000}") BigDecimal maxOrderAmount) {
        this.maxOrderAmount = maxOrderAmount;
    }

    @Override
    public Optional<RiskDecision> evaluate(RiskContext context) {
        if (context.orderAmount().compareTo(maxOrderAmount) > 0) {
            return Optional.of(new RiskDecision("MAX_ORDER_AMOUNT", "Order amount exceeds configured limit"));
        }
        return Optional.empty();
    }

    @Override
    public int order() {
        return 10;
    }
}
