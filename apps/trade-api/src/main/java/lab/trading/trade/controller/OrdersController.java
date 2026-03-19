package lab.trading.trade.controller;

import jakarta.validation.Valid;
import lab.trading.trade.application.OrderCommandService;
import lab.trading.trade.application.OrderQueryService;
import lab.trading.trade.dto.CreateOrderRequest;
import lab.trading.trade.dto.CreateOrderResponse;
import lab.trading.trade.dto.OrderDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    public OrdersController(OrderCommandService orderCommandService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderCommandService.placeOrder(request));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponse> get(@PathVariable String orderId) {
        return ResponseEntity.ok(orderQueryService.getOrder(orderId));
    }
}
