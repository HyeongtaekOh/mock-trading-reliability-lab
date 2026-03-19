package lab.trading.risk.controller;

import jakarta.validation.Valid;
import lab.trading.risk.application.FaultControlService;
import lab.trading.risk.dto.FaultProfileRequest;
import lab.trading.risk.dto.FaultProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/faults")
public class FaultAdminController {

    private final FaultControlService faultControlService;

    public FaultAdminController(FaultControlService faultControlService) {
        this.faultControlService = faultControlService;
    }

    @PostMapping("/profile")
    public ResponseEntity<Void> replace(@Valid @RequestBody FaultProfileRequest request) {
        faultControlService.replace(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<FaultProfileResponse> get() {
        return ResponseEntity.ok(faultControlService.get());
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> clear() {
        faultControlService.clear();
        return ResponseEntity.noContent().build();
    }
}
