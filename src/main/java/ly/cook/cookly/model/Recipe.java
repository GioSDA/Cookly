package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recipe")
public class Recipe {
    @Id
    private Integer id;

    //pre-recipe
    @TextIndexed(weight = 5)
    private String title;
    @TextIndexed(weight = 2)
    private String description;
    private LocalDate date; //when it was last updated

    @DBRef
    private ArrayList<Image> images;

    private int time;
    private int servings;

    //recipe
    @TextIndexed
    private ArrayList<String> ingredients;
    private HashMap<String, ArrayList<String>> steps;

    //post-recipe
    private String source;
    private String notes;
    private int averageRanking;

    @DBRef
    @TextIndexed
    private ArrayList<Comment> comments;

}
