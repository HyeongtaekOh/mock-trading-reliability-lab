package lab.trading.risk.fault;

import org.springframework.stereotype.Component;

@Component
public class CpuBurner {

    public void burn(long cpuBurnMs) {
        long deadline = System.nanoTime() + (cpuBurnMs * 1_000_000L);
        long sink = 0L;
        while (System.nanoTime() < deadline) {
            sink += System.nanoTime() % 7;
        }
        if (sink == Long.MIN_VALUE) {
            throw new IllegalStateException("Unreachable guard");
        }
    }
}
