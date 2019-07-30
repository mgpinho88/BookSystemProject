package com.company.BookStoreService.controller;

import com.company.BookStoreService.model.BookViewModel;
import com.company.BookStoreService.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/books") //Mapping to the "book" URI
public class BookController {

    @Autowired
    ServiceLayer service;

    //Add a book to the database
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel addBook(@RequestBody BookViewModel book) {
        return service.addBook(book);
    }

    //Find a book when given an ID
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBookById(@PathVariable("id") int bookId) {
        return service.getBook(bookId);
    }

    //Get all books
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        return service.getAllBooks();
    }

    //Update a book
    @PutMapping(value = "/{id}")
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
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") int bookId) {
        service.deleteBook(bookId);
    }
}
