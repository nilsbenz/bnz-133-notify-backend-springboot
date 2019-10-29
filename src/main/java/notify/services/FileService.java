package notify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import notify.entities.AppUser;
import notify.entities.File;
import notify.repositories.FileRepository;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileRepository fileRepository;

    private AuthService authService;

    @Autowired
    FileService(FileRepository fileRepository, AuthService authService) {
        this.fileRepository = fileRepository;
        this.authService = authService;
    }

    public File saveFile(MultipartFile multipartFile) {
        String name = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        AppUser user = authService.getCurrentUser();
        try {
            if (name.contains("..")) {
                throw new IllegalArgumentException();
            }

            File file = new File(name, multipartFile.getContentType(), multipartFile.getBytes(), user);

            return fileRepository.save(file);
        } catch (IOException ex) {
            throw new PersistenceException();
        }
    }

    public File getFile(String id) {
        return fileRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<String> getFileIds() {
        AppUser user = authService.getCurrentUser();
        return fileRepository.findAllFileIdsOfUser(user);
    }
}
