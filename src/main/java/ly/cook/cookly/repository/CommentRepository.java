package ly.cook.cookly.repository;

import ly.cook.cookly.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment findByPostTime(LocalDate postTime);

}
