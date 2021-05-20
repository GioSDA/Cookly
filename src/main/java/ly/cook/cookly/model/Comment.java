package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "comment")
public class Comment {

    @Id
    Integer id;

    @DBRef
    User user;
    String text;
    int rating;
    LocalDate postTime;
    int score; //likes/dislikes

}
