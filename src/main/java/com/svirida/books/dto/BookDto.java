package com.svirida.books.dto;

import com.svirida.books.models.Book;
import com.svirida.books.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;
    private String title;
    private String description;
    private String genre;
    private String writer;
    private List<Comment> comment;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getDescription(),
                book.getGenre(), book.getWriter(), book.getComment());
    }
    public static Book toDomainObject(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getDescription(),
                dto.getGenre(), dto.getWriter(), dto.getComment());
    }
}
