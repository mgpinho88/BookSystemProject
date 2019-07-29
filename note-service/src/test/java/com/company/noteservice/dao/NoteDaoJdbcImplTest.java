package com.company.noteservice.dao;

import com.company.noteservice.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoJdbcImplTest {
    @Autowired
    NoteDao dao;

    @Before
    public void setUp() throws Exception {
        List<Note> notes = dao.getAllNotes();

        notes.stream()
                .forEach(note -> dao.deleteNote(note.getNoteId()));
    }

    @Test
    public void addGetDeleteNote() {
        Note note = new Note();

        note.setBookId(2);
        note.setNote("This is a note");

        note = dao.addNote(note);

        Note fromDb = dao.getNoteById(note.getNoteId());

        assertEquals(note, fromDb);

        dao.deleteNote(note.getNoteId());

        fromDb = dao.getNoteById(note.getNoteId());

        assertNull(fromDb);
    }

    @Test
    public void getNotesByBook() {
        Note note = new Note();

        note.setBookId(2);
        note.setNote("This is a note");

        dao.addNote(note);
        dao.addNote(note);

        note.setBookId(3);

        dao.addNote(note);

        List<Note> notes = dao.getNotesByBook(2);

        assertEquals(2, notes.size());
    }

    @Test
    public void getAllNotes() {
        Note note = new Note();

        note.setBookId(2);
        note.setNote("This is a note");

        dao.addNote(note);
        dao.addNote(note);

        note.setBookId(3);

        dao.addNote(note);

        List<Note> notes = dao.getAllNotes();

        assertEquals(3, notes.size());
    }

    @Test
    public void updateNote() {
        Note note = new Note();

        note.setBookId(2);
        note.setNote("This is a note");

        note = dao.addNote(note);

        note.setNote("This note has been updated");

        dao.updateNote(note);

        Note fromDb = dao.getNoteById(note.getNoteId());

        assertEquals(note, fromDb);
    }
}