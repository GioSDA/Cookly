package ly.cook.cookly;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class CooklyApplication {

	private static final Log log = LogFactory.getLog(CooklyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CooklyApplication.class, args);
	}

}
