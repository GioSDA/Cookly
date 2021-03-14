package ly.cook.cookly;

import ly.cook.cookly.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CooklyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooklyApplication.class, args);
	}

}
