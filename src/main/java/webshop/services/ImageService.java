package webshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import webshop.entities.Image;
import webshop.repositories.ImageRepository;

import javax.persistence.PersistenceException;
import java.io.IOException;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(MultipartFile file) {
        String name = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(name.contains("..")) {
                throw new IllegalArgumentException();
            }

            Image image = new Image(name, file.getContentType(), file.getBytes());

            return imageRepository.save(image);
        } catch (IOException ex) {
            throw new PersistenceException();
        }
    }

    public Image getImage(String id) {
        return imageRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
