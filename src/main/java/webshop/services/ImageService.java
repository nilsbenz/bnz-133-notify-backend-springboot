package webshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import webshop.entities.AppUser;
import webshop.entities.Image;
import webshop.entities.License;
import webshop.repositories.ImageRepository;
import webshop.repositories.LicenseRepository;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    private LicenseRepository licenseRepository;

    private AuthService authService;

    @Autowired
    ImageService(ImageRepository imageRepository, LicenseRepository licenseRepository, AuthService authService) {
        this.imageRepository = imageRepository;
        this.licenseRepository = licenseRepository;
        this.authService = authService;
    }

    public Image saveImage(MultipartFile file) {
        String name = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (name.contains("..")) {
                throw new IllegalArgumentException();
            }

            Image image = new Image(name, file.getContentType(), file.getBytes());

            return imageRepository.save(image);
        } catch (IOException ex) {
            throw new PersistenceException();
        }
    }

    public Image getThumbnailImage(String id) {
        return getImage(id);
    }

    public Image getImage(String id) {
        return imageRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<String> getImageIds() {
        return imageRepository.findAllIds();
    }

    public void buyImage(String id) {
        Image image = imageRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        AppUser user = authService.getCurrentUser();
        licenseRepository.save(new License(image, user));
    }

    public boolean isLicensed(String id) {
        Image image = imageRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        AppUser user = authService.getCurrentUser();
        Optional<License> license = licenseRepository.getLicense(image, user);
        return license.isPresent();
    }
}
