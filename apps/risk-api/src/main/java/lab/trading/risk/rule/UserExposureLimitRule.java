package lab.trading.risk.rule;

import java.math.BigDecimal;
import java.util.Optional;
import lab.trading.risk.domain.RiskContext;
import lab.trading.risk.domain.RiskDecision;
import lab.trading.risk.domain.RiskRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserExposureLimitRule implements RiskRule {

    private final BigDecimal userExposureLimit;

    public UserExposureLimitRule(@Value("${app.rules.user-exposure-limit:2000000}") BigDecimal userExposureLimit) {
        this.userExposureLimit = userExposureLimit;
    }

    @Override
    public Optional<RiskDecision> evaluate(RiskContext context) {
        if (context.orderAmount().compareTo(userExposureLimit) > 0) {
            return Optional.of(new RiskDecision("USER_EXPOSURE_LIMIT", "Exposure exceeds configured threshold"));
        }
        return Optional.empty();
    }

    @Override
    public int order() {
        return 30;
    }
}
