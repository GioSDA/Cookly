package ly.cook.cookly.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "role")
public class Role {
    @Id
    private String id;

    private String role;

}
