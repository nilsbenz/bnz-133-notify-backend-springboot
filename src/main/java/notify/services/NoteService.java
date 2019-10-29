package notify.services;

import notify.entities.AppUser;
import notify.entities.Note;
import notify.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    private AuthService authService;

    @Autowired
    NoteService(NoteRepository noteRepository, AuthService authService) {
        this.noteRepository = noteRepository;
        this.authService = authService;
    }

    public Note saveNote(Note note) {
        AppUser user = authService.getCurrentUser();
        note.setUser(user);
        return noteRepository.save(note);
    }

    public List<Note> getNotes() {
        AppUser user = authService.getCurrentUser();
        return noteRepository.findAllOfUser(user);
    }
}
