package com.company.BookStoreService.dao;

import com.company.BookStoreService.model.Book;

import java.util.List;

public interface BookDao {

    //--Standard CRUD methods---------------------------//
    //Create a new book
    Book addBook(Book book);

    //Read
    //Get a book by ID
    Book getBook(int id);

    //Get a list of all books
    List<Book> getAllBooks();

    //Update a book
    Book updateBook(Book book);

    //Delete a book
    void deleteBook(int id);
//--------------------------------------------------//
}
