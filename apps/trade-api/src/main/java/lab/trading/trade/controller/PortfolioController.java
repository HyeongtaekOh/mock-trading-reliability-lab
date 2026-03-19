package lab.trading.trade.controller;

import lab.trading.trade.application.PortfolioQueryService;
import lab.trading.trade.dto.PortfolioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioQueryService portfolioQueryService;

    public PortfolioController(PortfolioQueryService portfolioQueryService) {
        this.portfolioQueryService = portfolioQueryService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PortfolioResponse> get(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioQueryService.get(userId));
    }
}
