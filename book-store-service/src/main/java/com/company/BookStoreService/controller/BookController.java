package com.company.BookStoreService.controller;

import com.company.BookStoreService.model.BookViewModel;
import com.company.BookStoreService.model.NoteViewModel;
import com.company.BookStoreService.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class BookController {

    @Autowired
    ServiceLayer service;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Books~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Add a book to the database
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel addBook(@RequestBody BookViewModel book) {
        return service.addBook(book);
    }

    //Find a book when given an ID
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBookById(@PathVariable("id") int bookId) {
        return service.getBook(bookId);
    }

    //Get all books
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        return service.getAllBooks();
    }

    //Update a book
    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable("id") int bookId, @RequestBody BookViewModel book) {

        if (book.getId() == 0) {
            book.setId(bookId);
        }
        if (bookId != book.getId()) {
            throw new IllegalArgumentException("Book ID in path does not match the Book ID in the request.");
        }

        service.updateBook(book);
    }

    //Delete a book
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") int bookId) {
        service.deleteBook(bookId);
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Notes~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Create a new note
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<NoteViewModel> addNote(@RequestBody NoteViewModel note) {
        return service.addNote(note);
    }

    //Get a note
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public NoteViewModel getNoteById(@PathVariable("id") int noteId) {
        return service.getNote(noteId);
    }

    //Get all notes by book
    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<NoteViewModel> getNotesByBook(@PathVariable("book_id") int bookId) {
        return service.getNotesByBook(bookId);
    }

    //Get all notes
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<NoteViewModel> getAllNotes() {
        return service.getAllNotes();
    }

    //Update a note
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNote(@PathVariable("id") int noteId) {
        service.deleteNote(noteId);
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
}
