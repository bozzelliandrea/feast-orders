package arch.aop;

import arch.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public LoggerAspect loggerAspect(@Autowired ErrorRepository errorRepository) {
        return new LoggerAspect(errorRepository);
    }

    @Bean
    public ControllerAspect controllerAspect() {
        return new ControllerAspect();
    }
}
