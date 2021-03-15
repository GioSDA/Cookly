package ly.cook.cookly;

import ly.cook.cookly.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class CooklyApplication {

	private static final Log log = LogFactory.getLog(CooklyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CooklyApplication.class, args);
	}

}
