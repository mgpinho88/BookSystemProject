package com.company.BookStoreService.controller;

import com.company.BookStoreService.model.NoteViewModel;
import com.company.BookStoreService.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/notes") //Mapping to the "note" URI
public class NoteController {

    @Autowired
    ServiceLayer service;

    //Create a new note
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteViewModel addNote(@RequestBody NoteViewModel note) {
        return service.addNote(note);
    }

    //Get a note
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoteViewModel getNoteById(@PathVariable("id") int noteId) {
        return service.getNote(noteId);
    }

    //Get all notes by book
    @GetMapping("/book/{book_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteViewModel> getNotesByBook(@PathVariable("book_id") int bookId) {
        return service.getNotesByBook(bookId);
    }

    //Get all notes
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NoteViewModel> getAllNotes() {
        return service.getAllNotes();
    }

    //Update a note
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNote(@PathVariable("id") int noteId, NoteViewModel note) {
        if (note.getNoteId() == 0) {
            note.setBookId(noteId);
        }
        if (note.getNoteId() != noteId) {
            throw new IllegalArgumentException("Note ID in the path does not match the Note ID in the request");
        }

        service.updateNote(noteId, note);
    }

    //Delete a note
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNote(@PathVariable("id") int noteId) {
        service.deleteNote(noteId);
    }
}
