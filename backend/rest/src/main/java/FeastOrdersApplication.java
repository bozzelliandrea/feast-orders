import arch.aop.AspectConfig;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"business", "controller", "arch"})
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = {"atomic/repository", "arch/repository"})
@EnableJpaAuditing
@Configuration
@Import({AspectConfig.class})
@EntityScan(basePackages = {"atomic/entity", "arch/entity"})
@EnableTransactionManagement
public class FeastOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeastOrdersApplication.class, args);
    }

    @Bean(name = "H2Config")
    @Profile("!prod")
    ServletRegistrationBean<WebServlet> h2servletRegistration() {
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<>(new WebServlet());
        registrationBean.addUrlMappings("/h2/*");
        return registrationBean;
    }
}


