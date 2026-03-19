package lab.trading.trade.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionJpaRepository extends JpaRepository<PositionEntity, PositionEntity.PositionId> {

    List<PositionEntity> findByUserId(Long userId);
}
