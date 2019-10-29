package notify.controller;

import notify.entities.File;
import notify.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {

    private FileService fileService;

    @Autowired
    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/api/files", method = RequestMethod.GET)
    public List<String> getFileIds() {
        return fileService.getFileIds();
    }

    @RequestMapping(value = "/api/files/{id}", method = RequestMethod.GET)
    public ResponseEntity getFile(@PathVariable("id") String id) {
        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @RequestMapping(value = "/api/files", method = RequestMethod.POST)
    public String saveFile(@RequestParam("file") MultipartFile multipartFile) {
        File file = fileService.saveFile(multipartFile);
        return file.getId();
    }
}
