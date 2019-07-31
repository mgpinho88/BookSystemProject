package com.company.BookStoreService.service;

import com.company.BookStoreService.dao.BookDao;
import com.company.BookStoreService.model.Book;
import com.company.BookStoreService.model.BookViewModel;
import com.company.BookStoreService.util.message.Note;
import com.company.BookStoreService.model.NoteViewModel;
import com.company.BookStoreService.util.fiegn.NoteClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

//--Setup-------------------------------------------//
    //Dependency Injections
    @Autowired
    private BookDao bdao;

    @Autowired
    private NoteClient client;

    //Setup the Queue Producer ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.requests.from.service";

    @Autowired
    private RabbitTemplate rabbit;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Instantiate the DAO and Client
    public ServiceLayer(BookDao bdao, NoteClient client, RabbitTemplate rabbit) {
        this.bdao = bdao;
        this.client = client;
        this.rabbit = rabbit;
    }
//--------------------------------------------------//

//--Book Methods------------------------------------//
    @Transactional
    public BookViewModel addBook(BookViewModel bvm) {

        Book book = buildBook(bvm);

        List<NoteViewModel> notes = bvm.getNotes();

        book = bdao.addBook(book);

        //List<NoteViewModel> notes2 = new ArrayList<>();

        Note msg;

        if (notes.size() > 0) {

            for (NoteViewModel note : notes) {
                note.setBookId(book.getId());

                msg = buildNoteMessage(note);

                rabbit.convertAndSend(EXCHANGE, ROUTING_KEY, msg);

            }
        }

        bvm.setId(book.getId());

        bvm.setNotes(client.getAllNotesByBook(bvm.getId()));

        return bvm;
    }

    public BookViewModel getBook(int id) {

        BookViewModel bookViewModel = buildBookViewModel(bdao.getBook(id));

        bookViewModel.setNotes(client.getAllNotesByBook(id));

        return bookViewModel;
    }

    public List<BookViewModel> getAllBooks() {

        List<BookViewModel> bvmList = new ArrayList<>();

        List<Book> books = bdao.getAllBooks();

        for (Book book : books) {
            BookViewModel bookViewModel = buildBookViewModel(book);
            bookViewModel.setNotes(client.getAllNotesByBook(book.getId()));

            bvmList.add(bookViewModel);
        }

        return bvmList;
    }

    public void updateBook(BookViewModel bvm) {

        Book book = buildBook(bvm);


        bdao.updateBook(book);

    }

    @Transactional
    public void deleteBook(int id) {

        List<NoteViewModel> notes = client.getAllNotesByBook(id);

        for (NoteViewModel note : notes) {
            client.deleteNote(note.getNoteId());

        }

        bdao.deleteBook(id);
    }
//--------------------------------------------------//

//--Note Methods------------------------------------//
    public List<NoteViewModel> addNote(NoteViewModel nvm) {

        Note msg = buildNoteMessage(nvm);
        rabbit.convertAndSend(EXCHANGE, ROUTING_KEY, msg);

        int bookId = nvm.getBookId();

        List<NoteViewModel> notes = client.getAllNotesByBook(bookId);
        return notes;
    }

    public NoteViewModel getNote(int id) {
        return client.getNote(id);
    }

    public List<NoteViewModel> getAllNotes() {
        return client.getAllNotes();
    }

    public List<NoteViewModel> getNotesByBook (int bookId) {
        return client.getAllNotesByBook(bookId);
    }

    public void updateNote(int noteID, NoteViewModel nvm) {
        Note msg = buildNoteMessage(nvm);
        rabbit.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
    }

    public void deleteNote(int id) {
        client.deleteNote(id);
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

//--NoteViewModel to message-----------------------//
    private Note buildNoteMessage(NoteViewModel note) {

        //Instantiate a Book View Model
        Note msg = new Note(
                note.getNoteId(),
                note.getBookId(),
                note.getNote());

        //Return a Book View Model
        return msg;
    }
//--------------------------------------------------//
}
