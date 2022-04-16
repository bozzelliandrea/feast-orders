package arch.config;

import arch.component.LifeCycleStrategy;
import arch.exception.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;

@Configuration
public class StrategyConfig {

    private final ApplicationContext applicationContext;

    public StrategyConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public void loadStrategy() throws Exception {
        Map<String, LifeCycleStrategy> beans = applicationContext.getBeansOfType(LifeCycleStrategy.class);
        for (String beanName : beans.keySet()) {
            Object instance = applicationContext.getBean(beanName);

            Method onBoot = instance.getClass().getMethod("onBoot");
            onBoot.invoke(instance);

            Thread onShutdownThread = new Thread(() -> {
                try {
                    Method onShutdown = instance.getClass().getMethod("onShutdown");
                    onShutdown.invoke(instance);
                } catch (Exception e) {
                    SneakyThrows.execute(e);
                }
            });
            Runtime.getRuntime().addShutdownHook(onShutdownThread);
        }
    }
}
