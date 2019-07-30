package com.company.BookStoreService.controller;

import com.company.BookStoreService.model.Book;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/notes") //Mapping to the "book" URI
public class NoteController {

    //Create a new note
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addNote(@RequestBody Book book) { //TODO - Book needs to be updated to View Model
        return null;
    }

    //Get a note
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getNoteById(@PathVariable("id") int noteId) { //TODO - Book needs to be updated to View Model
        return null;
    }

    //Get all notes by book
    @GetMapping("/book/book_id")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getNotesByBook(@PathVariable("book_id") int bookId) { //TODO - Book needs to be updated to View Model
        return null;
    }

    //Get all notes
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllNotes() { //TODO - Book needs to be updated to View Model
        return null;
    }

    //Update a note
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNote(@PathVariable("id") int noteId, Book book) { //TODO - Book needs to be updated to View Model

    }

    //Delete a note
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNote(@PathVariable("id") int noteId) {

    }
}
