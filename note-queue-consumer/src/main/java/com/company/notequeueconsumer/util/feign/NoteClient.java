package com.company.notequeueconsumer.util.feign;

import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "note-service")
public interface NoteClient {

    //Create a note
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public void addNote(Note note);

    //Update a note
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable("id") int noteId, Note note);

}
