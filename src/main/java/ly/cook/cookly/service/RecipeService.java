package ly.cook.cookly.service;

import ly.cook.cookly.model.Recipe;
import ly.cook.cookly.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe loadRecipeById(String id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe loadRecipeByTitle(String title) {
        return recipeRepository.findByTitle(title);
    }

    public List<Recipe> loadAllRecipes() {
        return recipeRepository.findAll();
    }

}