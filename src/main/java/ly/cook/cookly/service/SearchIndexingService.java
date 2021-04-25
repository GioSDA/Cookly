package ly.cook.cookly.service;

import ly.cook.cookly.model.Recipe;
import ly.cook.cookly.repository.RecipeRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.HashMap;

@EnableAsync
public class SearchIndexingService {

    private RecipeRepository recipeRepository;

    private HashMap<String, ArrayList<Recipe>> searchResults;

    @Async
    @Scheduled(fixedDelay = 3600000, initialDelay = 100000)
    public void indexSearchResults() {

    }
}
