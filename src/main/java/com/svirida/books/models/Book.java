package com.svirida.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "book")
public class Book {
    @Id
    private long id;
    private String title;
    private String description;
    private String genre;
    private String writer;
    @DBRef
    private List<Comment> comment;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", writer=" + writer +
                '}';
    }
}
