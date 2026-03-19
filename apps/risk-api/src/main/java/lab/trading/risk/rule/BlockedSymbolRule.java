package lab.trading.risk.rule;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import lab.trading.risk.domain.RiskContext;
import lab.trading.risk.domain.RiskDecision;
import lab.trading.risk.domain.RiskRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlockedSymbolRule implements RiskRule {

    private final List<String> blockedSymbols;

    public BlockedSymbolRule(@Value("${app.rules.blocked-symbols:}") String blockedSymbols) {
        this.blockedSymbols = Arrays.stream(blockedSymbols.split(","))
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .map(value -> value.toUpperCase(Locale.ROOT))
                .toList();
    }

    @Override
    public Optional<RiskDecision> evaluate(RiskContext context) {
        if (blockedSymbols.contains(context.symbol().toUpperCase(Locale.ROOT))) {
            return Optional.of(new RiskDecision("BLOCKED_SYMBOL", "Symbol is blocked for trading"));
        }
        return Optional.empty();
    }

    @Override
    public int order() {
        return 20;
    }
}
