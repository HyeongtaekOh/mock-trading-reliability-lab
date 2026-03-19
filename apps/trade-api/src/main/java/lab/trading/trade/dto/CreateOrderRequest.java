package lab.trading.trade.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateOrderRequest(
        @NotNull Long userId,
        @NotBlank String symbol,
        @NotBlank String side,
        @NotNull @Min(1) Integer quantity,
        @NotNull BigDecimal price
) {
}
