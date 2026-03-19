package lab.trading.trade.application;

import lab.trading.trade.client.RiskAssessmentResult;
import lab.trading.trade.dto.CreateOrderRequest;
import lab.trading.trade.dto.CreateOrderResponse;
import lab.trading.trade.persistence.OrderEntity;
import lab.trading.trade.persistence.OrderJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RejectedOrderWriter {

    private final OrderJpaRepository orderJpaRepository;

    public RejectedOrderWriter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Transactional
    public CreateOrderResponse write(CreateOrderRequest request, RiskAssessmentResult result) {
        OrderEntity entity = OrderEntity.rejected(request, result.reasonCode(), result.reasonMessage());
        orderJpaRepository.save(entity);
        return new CreateOrderResponse(entity.getId(), entity.getStatus().name());
    }
}
