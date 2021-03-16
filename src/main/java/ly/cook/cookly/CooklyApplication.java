package ly.cook.cookly;

import com.mongodb.client.MongoClients;
import ly.cook.cookly.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class CooklyApplication {

	private static final Log log = LogFactory.getLog(CooklyApplication.class);

	public static void main(String[] args) {
		MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "database");
		mongoOps.insert(new Person("Joe", 34));

		log.info(mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class));

		mongoOps.dropCollection("person");
	}

}
