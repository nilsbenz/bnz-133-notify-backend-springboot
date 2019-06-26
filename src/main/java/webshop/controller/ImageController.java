package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import webshop.entities.Image;
import webshop.services.ImageService;

import java.util.List;

@RestController
public class ImageController {

    private ImageService imageService;

    @Autowired
    ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/api/images", method = RequestMethod.GET)
    public List<String> getImageIds() {
        return imageService.getImageIds();
    }

    @RequestMapping(value = "/api/images/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImage(@PathVariable("id") String id) {
        Image image = imageService.getThumbnailImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    @RequestMapping(value = "/api/images", method = RequestMethod.POST)
    public String saveImage(@RequestParam("image") MultipartFile file) {
        Image image = imageService.saveImage(file);
        return image.getId();
    }

    @RequestMapping(value = "/api/images/buy/{id}", method = RequestMethod.POST)
    public void buyImage(@PathVariable("id") String id) {
        imageService.buyImage(id);
    }

    @RequestMapping(value = "/api/images/licensed/{id}", method = RequestMethod.GET)
    public boolean isLicensed(@PathVariable("id") String id) {
        return imageService.isLicensed(id);
    }

    @RequestMapping(value = "/api/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadImage(@PathVariable("id") String id) {
        Image image = imageService.getImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }
}
