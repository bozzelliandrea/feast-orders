import arch.aop.AspectConfig;
import arch.config.PersistenceConfig;
import arch.config.SchedulerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"business", "controller", "arch"})
@Import({AspectConfig.class, PersistenceConfig.class, SchedulerConfig.class})
public class FeastOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeastOrdersApplication.class, args);
    }
}


