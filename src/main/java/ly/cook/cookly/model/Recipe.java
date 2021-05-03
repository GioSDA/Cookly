package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recipe")
public class Recipe {
    @Id
    private Integer id;

    //pre-recipe
    private String title;
    private String description;

    private ArrayList<String> images;
    private int time;
    private int servings;

    //recipe
    private ArrayList<String> ingredients;
    private ArrayList<String> steps;

    //post-recipe
    private String source;
    private String notes;
    private int averageRanking;

    @DBRef
    private ArrayList<Comment> comments;

}
