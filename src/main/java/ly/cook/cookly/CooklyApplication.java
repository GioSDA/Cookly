package ly.cook.cookly;

import ly.cook.cookly.repository.RecipeRepository;
import ly.cook.cookly.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories(basePackageClasses = {
		UserRepository.class, RecipeRepository.class
})
public class CooklyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooklyApplication.class, args);
	}

}
