package ly.cook.cookly.repository;

import ly.cook.cookly.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

    Recipe findByTitle(String title);

}
