package lab.trading.trade.application;

import lab.trading.trade.dto.OrderDetailResponse;
import lab.trading.trade.persistence.OrderReader;
import org.springframework.stereotype.Service;

@Service
public class OrderQueryService {

    private final OrderReader orderReader;

    public OrderQueryService(OrderReader orderReader) {
        this.orderReader = orderReader;
    }

    public OrderDetailResponse getOrder(String orderId) {
        return OrderDetailResponse.from(orderReader.findByIdOrThrow(orderId));
    }
}
