package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetails {
    String title;
    String description;

    int time;
    int servings;

    ArrayList<String> ingredients;
    HashMap<String, ArrayList<String>> steps;
}
