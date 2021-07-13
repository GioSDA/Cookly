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
import java.util.List;

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
    @TextIndexed(weight = 2)
    private String authorName;
    private String authorId;

    @DBRef
    private ArrayList<Image> images;

    @TextIndexed
    private int time;
    @TextIndexed
    private String servings;

    //recipe
    @TextIndexed
    private ArrayList<String> ingredients;
    @TextIndexed
    private ArrayList<String> steps;

    //post-recipe
    @TextIndexed
    private String source;
    @TextIndexed
    private String notes;
    private int averageRanking;

    private Nutrients nutrients;

    @DBRef
    private ArrayList<Comment> comments;

    public void calcAverageRankings() {
        int ranking = 0;

        for (Comment c : comments) {
            ranking += c.getRating();
        }

        this.averageRanking = ranking / comments.size();
    }

}
