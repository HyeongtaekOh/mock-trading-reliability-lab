package lab.trading.trade.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import lab.trading.trade.domain.PortfolioPositionView;

@Entity
@Table(name = "positions")
@IdClass(PositionEntity.PositionId.class)
public class PositionEntity {

    @Id
    private Long userId;

    @Id
    private String symbol;

    private Integer quantity;

    private BigDecimal avgPrice;

    private Instant updatedAt;

    @Version
    private Long version;

    protected PositionEntity() {
    }

    public PortfolioPositionView toView() {
        return new PortfolioPositionView(symbol, quantity, avgPrice);
    }

    public static final class PositionId implements Serializable {
        private Long userId;
        private String symbol;

        public PositionId() {
        }
    }
}
