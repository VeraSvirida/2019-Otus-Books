package com.svirida.books.repositories;

import com.svirida.books.models.Writer;

import java.util.Optional;

public interface WriterRepositoryJpa {
    Optional<Writer> getById(long id);
}
