package com.company.BookStoreService.model;

import java.util.List;
import java.util.Objects;

public class BookViewModel {

//--States------------------------------------------//
    private int id;
    private String title;
    private String author;
    private List<NoteViewModel> notes;
//--------------------------------------------------//

//--Getters and Setters-----------------------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<NoteViewModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteViewModel> notes) {
        this.notes = notes;
    }
//--------------------------------------------------//

//--Equals and Hash---------------------------------//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return id == that.id &&
                title.equals(that.title) &&
                author.equals(that.author) &&
                notes.equals(that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, notes);
    }
//--------------------------------------------------//
}
