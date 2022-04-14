package business.stats;

import arch.component.LifeCycleStrategy;
import org.springframework.stereotype.Service;

@Service
public class StatsService implements LifeCycleStrategy {

    @Override
    public void onBoot() {
        System.out.println("ON BOOT STATS");
    }

    @Override
    public void onShutdown() {
        System.out.println("ON SHUTDOWN STATS");
    }
}
