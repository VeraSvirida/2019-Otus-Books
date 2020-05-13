package com.svirida.books.repositories;

import com.svirida.books.models.Genre;

import java.util.Optional;

public interface GenreRepositoryJpa {
    Optional<Genre> getById(long id);
}
