package vn.com.gigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("vn.com.gigo.repository")
@EntityScan(basePackages = { "vn.com.gigo.entity" })
@EnableJpaAuditing
public class GigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigoApplication.class, args);
	}

}
