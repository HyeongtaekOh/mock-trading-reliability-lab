package lab.trading.risk.controller;

import jakarta.validation.Valid;
import lab.trading.risk.application.RiskCheckService;
import lab.trading.risk.dto.RiskCheckRequest;
import lab.trading.risk.dto.RiskCheckResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class RiskCheckController {

    private final RiskCheckService riskCheckService;

    public RiskCheckController(RiskCheckService riskCheckService) {
        this.riskCheckService = riskCheckService;
    }

    @PostMapping("/check")
    public ResponseEntity<RiskCheckResponse> check(@Valid @RequestBody RiskCheckRequest request) {
        return ResponseEntity.ok(riskCheckService.check(request));
    }
}
