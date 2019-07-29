package com.company.BookStoreService.dao;

import com.company.BookStoreService.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    BookDao bdao;

    //--Setup the test environment----------------------//
    //Clear out the database before testing
    @Before
    public void setUp() {

        //Make a list of books
        List<Book> bookList = bdao.getAllBooks();

        //Stream through all the books
        bookList.stream().forEach(
                //Delete each of the books in the stream
                book -> bdao.deleteBook(book.getId())
        );
    }
//--------------------------------------------------//

//--Test the implementations------------------------//
    @Test
    public void addGetDelete() {

        //Make a book that will be added to the database
        Book book = new Book();

        book.setTitle("The Witcher");
        book.setAuthor("Andrzej Sapkowski");

        //Pass the book database and get book's id
        book = bdao.addBook(book);

        //Create a new book object with data from database
        Book fromDatabase = bdao.getBook(book.getId());

        //Check if the two objects are equal
        assertEquals(book, fromDatabase);

        //Delete the book data from the database
        bdao.deleteBook(book.getId());

        //Set the book object to the deleted data
        fromDatabase = bdao.getBook(book.getId());

        //Check if the object is null
        assertNull(fromDatabase);
    }

    @Test
    public void getAll() {

        //Create a 2 book objects
        Book bookOne = new Book();
        Book bookTwo = new Book();


        bookOne.setTitle("The Witcher");
        bookOne.setAuthor("Andrzej Sapkowski");


        bookTwo.setTitle("A Game of Thrones");
        bookTwo.setAuthor("George R.R. Martin");

        //Pass the books into the database
        bookOne = bdao.addBook(bookOne);
        bookTwo = bdao.addBook(bookTwo);

        //Make a list of books from the database
        List<Book> books = bdao.getAllBooks();

        //Check the size of the list
        assertEquals(2, books.size());
    }

    @Test
    public void update() {

        //Create a book
        Book book = new Book();

        book.setTitle("The Witcher");
        book.setAuthor("Andrzej Sapkowski");

        //Add it to the database and get the ID
        book = bdao.addBook(book);

        //Update the book and pass the updates to the database
        book.setTitle("A Game of Thrones");
        book.setAuthor("George R.R. Martin");

        bdao.updateBook(book);

        //Create a new book object with data from the database
        Book fromDatabase = bdao.getBook(book.getId());

        //Check if the two book objects are equal
        assertEquals(book, fromDatabase);

    }
//--------------------------------------------------//
}
