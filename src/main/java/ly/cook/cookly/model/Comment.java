package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class Comment {

    @Id
    String id;

    @DBRef
    User user;
    String text;
    int rating;
    LocalDate postTime;

    public Comment(User user, String text, int rating, LocalDate postTime, int score) {
        this.user = user;
        this.text = text;
        this.rating = rating;
        this.postTime = postTime;
    }

}
