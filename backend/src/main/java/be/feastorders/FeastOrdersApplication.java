package be.feastorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan(basePackages = {
		"business.category",
		"business.menuitem",
		"business.order",
		"business.printer",
		"business.site",
		"business.user",
})
public class FeastOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeastOrdersApplication.class, args);
	}

}
