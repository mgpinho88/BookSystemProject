package com.company.BookStoreService.service;

import com.company.BookStoreService.dao.BookDao;
import com.company.BookStoreService.model.Book;
import com.company.BookStoreService.model.BookViewModel;
import com.company.BookStoreService.model.NoteViewModel;
import com.company.BookStoreService.util.fiegn.NoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

//--Setup-------------------------------------------//
    //Dependency Injections
    @Autowired
    private BookDao bdao;

    @Autowired
    private NoteClient client;

    //Instantiate the DAO and Client
    public ServiceLayer(BookDao bdao, NoteClient client) {
        this.bdao = bdao;
        this.client = client;
    }
//--------------------------------------------------//

//--Book Methods------------------------------------//
    public BookViewModel addBook(BookViewModel bvm) {
        return null;
    }

    public BookViewModel getBook(int id) {
        return null;
    }

    public List<BookViewModel> getAllBooks() {
        return null;
    }

    public void updateBook(BookViewModel bvm) {

    }

    public void deleteBook(int id) {

    }
//--------------------------------------------------//

//--Note Methods------------------------------------//
    public NoteViewModel addNote(NoteViewModel nvm) {
        return null;
    }

    public NoteViewModel getNote(int id) {
        return null;
    }

    public List<NoteViewModel> getAllNotes() {
        return null;
    }

    public List<NoteViewModel> getNotesByBook (int bookId) {
        return null;
    }

    public void updateNote(NoteViewModel nvm) {

    }

    public void deleteNote(int id) {

    }
//--------------------------------------------------//

//--Book object to View Model-----------------------//
    private BookViewModel buildBookViewModel(Book book) {

        //Instantiate a Book View Model
        BookViewModel bvm = new BookViewModel();

        //Convert data from the book object to the view model
        bvm.setId(book.getId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());

        //Make a list of notes
        List<NoteViewModel> notes = client.getAllNotes();

        //Return a Book View Model
        return bvm;
    }
//--------------------------------------------------//

// --Book View model to dto-------------------------//
    private Book buildBook(BookViewModel bvm) {
        // Instantiate a Book
        Book book = new Book();

        // set book data from view model
        book.setId(bvm.getId());
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());


        return book;
    }
//--------------------------------------------------//
}
