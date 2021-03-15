package ly.cook.cookly.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private Integer id;

    private String username;
    private String password;
    private boolean active;

    public String toString() {
        return String.format("Customer[id=%s, username=%s, password=%s, active=%s]", id, username, password, active);
    }
}
