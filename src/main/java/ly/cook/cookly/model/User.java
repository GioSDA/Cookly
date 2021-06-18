package ly.cook.cookly.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String email;
    private String password;
    private String name;
    private boolean enabled;
    private HashMap<String, ArrayList<String>> info;

    @DBRef
    private List<Recipe> recipes;

    @DBRef
    private Image image;

    @DBRef
    private Set<Role> roles;

    public Image getImage() {
        if (image != null) return image;
        else return new Image("/images/users/defaultuser.jpg");
    }
}
