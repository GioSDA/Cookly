package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    //pre-recipe
    ArrayList<Image> images;
    int prepTime;
    int cookTime;
    int time;
    int servings;

    //recipe
    ArrayList<String> ingredients;
    ArrayList<String> steps;

    //post-recipe
    String notes;
    ArrayList<Comment> comments;

}
