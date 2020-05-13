package com.svirida.books.repositories;

import com.svirida.books.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    Book save(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    void updateDescriptionByIb(long id, String description);
}
