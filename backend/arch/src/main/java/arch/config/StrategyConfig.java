package arch.config;

import arch.component.LifeCycleStrategy;
import arch.exception.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.lang.reflect.Method;
import java.util.Map;

@Configuration
public class StrategyConfig {

    private final ApplicationContext applicationContext;

    public StrategyConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @EventListener
    public void loadBootStrategy(ContextRefreshedEvent ctx) throws Exception {
        Map<String, LifeCycleStrategy> beans = applicationContext.getBeansOfType(LifeCycleStrategy.class);
        for (String beanName : beans.keySet()) {
            Object instance = applicationContext.getBean(beanName);

            Method onBoot = instance.getClass().getMethod("onBoot");
            onBoot.invoke(instance);
        }
    }

    // TODO: testare con salvataggio record, in caso valutare il @PreDestroy
    @Bean
    public void loadShutdownStrategy() throws Exception {
        Map<String, LifeCycleStrategy> beans = applicationContext.getBeansOfType(LifeCycleStrategy.class);
        for (String beanName : beans.keySet()) {
            Object instance = applicationContext.getBean(beanName);

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
