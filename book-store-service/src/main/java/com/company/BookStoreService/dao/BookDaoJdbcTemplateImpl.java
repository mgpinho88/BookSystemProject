package com.company.BookStoreService.dao;

import com.company.BookStoreService.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {

//--JDBC--------------------------------------------//
    JdbcTemplate jdbc;

    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
//--------------------------------------------------//

//--Prepared Statements-----------------------------//
    public static final String INSERT_BOOK_SQL = "insert into book (title, author) values (?, ?)";
    public static final String GET_BOOK_SQL = "select * from book where book_id = ?";
    public static final String GET_ALL_BOOK_SQL = "select * from book";
    public static final String UPDATE_BOOK_SQL = "update book set title = ?, author = ? where book_id = ?";
    public static final String DELETE_BOOK_SQL = "delete from book where book_id = ?";
//--------------------------------------------------//

//--Method Implementations--------------------------//
    @Override
    @Transactional
    public Book addBook(Book book) {

        //Use JDBC to update the database
        jdbc.update(
                INSERT_BOOK_SQL,
                book.getTitle(),
                book.getAuthor()
        );

        //Use JDBC to get the ID of the book
        int bookId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);

        //Set the id of the book object
        book.setId(bookId);

        //Return the book object to the user
        return book;
    }

    @Override
    public Book getBook(int id) {
        try {
            //Use JDBC to query for a book
            Book book = jdbc.queryForObject(GET_BOOK_SQL, this::mapRowToBook, id);

            //Return the book with data from database
            return book;
        } catch (EmptyResultDataAccessException e) {
            //If there is no data for a book return null
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        //Use JDBC to get a list of all the books in the database
        List<Book> books = jdbc.query(GET_ALL_BOOK_SQL, this::mapRowToBook);

        //Return the list of books
        return books;
    }

    @Override
    public Book updateBook(Book book) {

        //Use JSBC to update the book
        jdbc.update(
                UPDATE_BOOK_SQL,
                //Pass in the new information to the database
                book.getTitle(),
                book.getAuthor(),

                //Search the database for the specified book ID
                book.getId()
        );

        //Return the updated book back to the user
        return book;
    }

    @Override
    public void deleteBook(int id) {

        //Use JDBC to delete a book - do not return any object
        jdbc.update(DELETE_BOOK_SQL, id);
    }
//--------------------------------------------------//

//--Helper Classes----------------------------------//
    private Book mapRowToBook(ResultSet result, int rowNum) throws SQLException {

        //Create a new book object
        Book b = new Book();

        //Set the values of the book object to the data in database
        b.setId(result.getInt("book_id"));
        b.setTitle(result.getString("title"));
        b.setAuthor(result.getString("author"));

        //Return the book
        return b;
    }
//--------------------------------------------------//
}
