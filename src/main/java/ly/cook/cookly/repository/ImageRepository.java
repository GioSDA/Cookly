package ly.cook.cookly.repository;

import ly.cook.cookly.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, Integer> {

    Image findByPath(String path);

}
