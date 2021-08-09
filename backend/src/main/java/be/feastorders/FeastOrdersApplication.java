package be.feastorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableSwagger2
@ComponentScan(basePackages = {"be.feastorders.business"})
public class FeastOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeastOrdersApplication.class, args);
	}

}


