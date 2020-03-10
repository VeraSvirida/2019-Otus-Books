package com.svirida.books.domain;

import lombok.ToString;

@ToString
public class Book {

    private long id;
    private String title;
    private String description;
    private Genre genre;
    private Writer writer;

    public Book(long id, String title, String description, Genre genre, Writer writer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.writer = writer;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
