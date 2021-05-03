package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "comment")
public class Comment {

    @Id
    Integer id;

    User user;
    String text;
    int rating;
    Date date;
    int score; //likes/dislikes

}
