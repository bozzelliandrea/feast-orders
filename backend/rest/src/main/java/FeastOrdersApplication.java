import arch.aop.AspectConfig;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"business", "controller", "arch"})
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = {"atomic/repository", "arch/repository"})
@EnableJpaAuditing
@EnableSwagger2
@Configuration
@Import({AspectConfig.class})
@EntityScan(basePackages = {"atomic/entity", "arch/entity"})
public class FeastOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeastOrdersApplication.class, args);
    }

    @Bean(name = "Swagger")
    @Profile("!prod")
    public Docket swagger() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("be.feastorders"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(name = "H2Config")
    @Profile("!prod")
    ServletRegistrationBean<WebServlet> h2servletRegistration() {

        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<>(new WebServlet());
        registrationBean.addUrlMappings("/h2/*");
        return registrationBean;
    }

}


