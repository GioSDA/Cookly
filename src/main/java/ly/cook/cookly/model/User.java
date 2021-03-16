package ly.cook.cookly.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String email;
    private String password;
    private String username;
    private boolean enabled;

    @DBRef
    private Set<Role> roles;
}
