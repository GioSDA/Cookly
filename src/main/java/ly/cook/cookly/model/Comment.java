package ly.cook.cookly.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Comment {

    User user;
    String text;
    int rating;
    Date date;
    int score; //likes/dislikes

}
