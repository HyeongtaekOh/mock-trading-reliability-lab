package lab.trading.risk.application;

import lab.trading.risk.dto.FaultProfileRequest;
import lab.trading.risk.dto.FaultProfileResponse;
import lab.trading.risk.fault.InMemoryFaultProfileStore;
import org.springframework.stereotype.Service;

@Service
public class FaultControlService {

    private final InMemoryFaultProfileStore store;

    public FaultControlService(InMemoryFaultProfileStore store) {
        this.store = store;
    }

    public void replace(FaultProfileRequest request) {
        store.replace(request.toProfile());
    }

    public FaultProfileResponse get() {
        return FaultProfileResponse.from(store.get());
    }

    public void clear() {
        store.clear();
    }
}
