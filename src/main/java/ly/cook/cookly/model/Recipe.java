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
    private String id;

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

    public Recipe(String title, String description, LocalDate date, ArrayList<Image> images, int time, int servings, ArrayList<String> ingredients, HashMap<String, ArrayList<String>> steps, String source, String notes) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.images = images;
        this.time = time;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
        this.source = source;
        this.notes = notes;
    }

    public void calcAverageRankings() {
        int ranking = 0;

        for (Comment c : comments) {
            ranking += c.getRating();
        }

        this.averageRanking = ranking / comments.size();
    }

}
