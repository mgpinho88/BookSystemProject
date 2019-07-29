package com.company.BookStoreService.controller;

import com.company.BookStoreService.model.Book;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/books") //Mapping to the "book" URI
public class BookController {

    //Add a book to the database
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        //Body of the addBook Method

        //Return an object
        return null;
    }

    //Find a book when given an ID
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable("id") int bookId) {
        //Body of the getBookById Method

        //Return and object
        return null;
    }

    //Get all books
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        //Body of the getAllBooks Method

        //Return a list of objects
        return null;
    }

    //Update a book
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable("id") int bookId, @RequestBody Book book) {
        //Body of the updateBook method

        //No return type for update
    }

    //Delete a book
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") int bookId) {
        //Body of deleteBook method

        //No return type
    }
}
