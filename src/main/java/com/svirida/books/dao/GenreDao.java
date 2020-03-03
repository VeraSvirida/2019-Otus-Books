package com.svirida.books.dao;

import com.svirida.books.domain.Genre;

public interface GenreDao {
    Genre getById(long id);
}
