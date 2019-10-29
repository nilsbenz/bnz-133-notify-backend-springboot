package notify.controller;

import notify.entities.Note;
import notify.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    private NoteService noteService;

    @Autowired
    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "/api/notes", method = RequestMethod.GET)
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @RequestMapping(value = "/api/notes", method = RequestMethod.POST)
    public Note saveNote(@RequestBody Note note) {
        return noteService.saveNote(note);
    }
}
