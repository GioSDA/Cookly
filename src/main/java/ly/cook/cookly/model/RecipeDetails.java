package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetails {
    String title;
    String description;

    List<MultipartFile> images;

    int minutes;
    int hours;

    String servings;

    String ingredients;
    String steps;

    private String source;
    private String notes;
}
