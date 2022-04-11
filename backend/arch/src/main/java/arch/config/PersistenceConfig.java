package arch.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = {"atomic/repository", "arch/repository"})
@EnableJpaAuditing
@EntityScan(basePackages = {"atomic/entity", "arch/entity"})
@EnableTransactionManagement
@Configuration
public class PersistenceConfig {

    @Bean(name = "H2Config")
    @Profile("!prod")
    ServletRegistrationBean<WebServlet> h2servletRegistration() {
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<>(new WebServlet());
        registrationBean.addUrlMappings("/h2/*");
        return registrationBean;
    }
}
