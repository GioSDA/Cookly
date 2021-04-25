package ly.cook.cookly;

import ly.cook.cookly.repository.RoleRepository;
import ly.cook.cookly.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {
		UserRepository.class, RoleRepository.class
})
public class CooklyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooklyApplication.class, args);
	}

}
