package ly.cook.cookly.service;

import ly.cook.cookly.model.Image;
import ly.cook.cookly.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public Image loadImageById(String id) {
        return imageRepository.findById(id).orElse(null);
    }

    public Image loadImageByPath(String path) {
        return imageRepository.findByPath(path);
    }

    public List<Image> loadAll() {
        return imageRepository.findAll();
    }

}