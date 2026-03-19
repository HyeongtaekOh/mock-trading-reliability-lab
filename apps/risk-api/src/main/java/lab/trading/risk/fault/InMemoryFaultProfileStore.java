package lab.trading.risk.fault;

import java.util.concurrent.atomic.AtomicReference;
import lab.trading.risk.domain.FaultProfile;
import org.springframework.stereotype.Component;

@Component
public class InMemoryFaultProfileStore {

    private final AtomicReference<FaultProfile> ref =
            new AtomicReference<>(new FaultProfile(0, 0, 0, null, null));

    public FaultProfile get() {
        return ref.get();
    }

    public void replace(FaultProfile profile) {
        ref.set(profile);
    }

    public void clear() {
        ref.set(new FaultProfile(0, 0, 0, null, null));
    }
}
