package com.company.noteservice.dao;

import com.company.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteDaoJdbcImpl implements NoteDao {
    // PREPARED STATEMENTS
    public static final String INSERT_NOTE =
            "insert into note (book_id, note) values (?, ?)";
    public static final String GET_ONE_NOTE =
            "select * from note where note_id = ?";
    public static final String GET_BY_BOOK =
            "select * from note where book_id = ?";
    public static final String GET_ALL_NOTE =
            "select * from note";
    public static final String UPDATE_NOTE =
            "update note set book_id = ?, note = ? where note_id = ?";
    public static final String DELETE_NOTE =
            "delete from note where note_id = ?";

    @Autowired
    JdbcTemplate jdbc;

    public NoteDaoJdbcImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    @Transactional
    public Note addNote(Note note) {
        jdbc.update(INSERT_NOTE, note.getBookId(), note.getNote());

        int id = jdbc.queryForObject("select last_insert_id()", Integer.class);

        note.setNoteId(id);
        return note;
    }

    @Override
    public Note getNoteById(int id) {
        try {
            return jdbc.queryForObject(GET_ONE_NOTE, this::mapRowToNote, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Note> getNotesByBook(int bookId) {
        return jdbc.query(GET_BY_BOOK, this::mapRowToNote, bookId);
    }

    @Override
    public List<Note> getAllNotes() {
        return jdbc.query(GET_ALL_NOTE, this::mapRowToNote);
    }

    @Override
    public Note updateNote(Note note) {
        jdbc.update(UPDATE_NOTE, note.getBookId(), note.getNote(), note.getNoteId());
        return note;
    }

    @Override
    public void deleteNote(int id) {
        jdbc.update(DELETE_NOTE, id);
    }

    // ROW MAPPER
    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setNoteId(rs.getInt("note_id"));
        note.setBookId(rs.getInt("book_id"));
        note.setNote(rs.getString("note"));
        return note;
    }
}
