package com.company.BookStoreService.model;

import java.util.Objects;

public class NoteViewModel {
    //--States------------------------------------------//
    private int id;
    private int bookId;
    private String note;
//--------------------------------------------------//

//--Getters and Setters-----------------------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
//--------------------------------------------------//

//--Equals and Hash---------------------------------//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteViewModel that = (NoteViewModel) o;
        return id == that.id &&
                bookId == that.bookId &&
                note.equals(that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, note);
    }
//--------------------------------------------------//

}
