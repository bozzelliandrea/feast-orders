package business.stats.config;

import business.stats.service.StatsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class StatsConfig {

    private final StatsService statsService;

    public StatsConfig(StatsService statsService) {
        this.statsService = statsService;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void loadStats() {
        statsService.loadStatsForDay();
    }
}
