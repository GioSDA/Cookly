package ly.cook.cookly.service;

import ly.cook.cookly.model.Image;
import ly.cook.cookly.model.Recipe;
import ly.cook.cookly.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe loadRecipeById(Integer id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe loadRecipeByTitle(String title) {
        return recipeRepository.findByTitle(title);
    }

}