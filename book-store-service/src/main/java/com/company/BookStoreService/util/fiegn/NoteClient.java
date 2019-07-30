package com.company.BookStoreService.util.fiegn;

import com.company.BookStoreService.model.NoteViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")
//Name of the file we are in our Git Repository that the service will communicate with
public interface NoteClient {

    //Create a note
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public NoteViewModel addNote(@RequestBody NoteViewModel note);

    //Get a note when given an ID
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public NoteViewModel getNote(@PathVariable("id") int noteId);

    //Get a list of all notes
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<NoteViewModel> getAllNotes();

    //Get a list of notes by Books
    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    public List<NoteViewModel> getAllNotesByBook(@PathVariable("book_id") int bookId);

    //Update a note
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable("id") int noteId, @RequestBody NoteViewModel note);

    //Delete a note
    @RequestMapping(value = "notes/{id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable("id") int noteId);
}
