package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/notes")
public class NoteController {
    @Autowired
    NoteDao noteDao;

    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteDao.addNote(note);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @GetMapping(value = "/{id}")
    public Note getNote(@PathVariable int id) {
        return noteDao.getNoteById(id);
    }

    @GetMapping(value = "/book/{book_id}")
    public List<Note> getNotesByBook(@PathVariable int book_id) {
        return noteDao.getNotesByBook(book_id);
    }

    @PutMapping(value = "/{id}")
    public void updateNote(@RequestBody Note note, @PathVariable int id) {
        if (note.getNoteId() == 0) {
            note.setNoteId(id);
        }

        noteDao.updateNote(note);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteNote(@PathVariable int id) {
        noteDao.deleteNote(id);
    }
}
