package com.company.noteservice.dao;

import com.company.noteservice.model.Note;

import java.util.List;

public interface NoteDao {
    Note addNote(Note note);
    Note getNoteById(int id);
    List<Note> getNotesByBook(int bookId);
    List<Note> getAllNotes();
    Note updateNote(Note note);
    void deleteNote(int id);
}
