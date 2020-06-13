package com.svirida.books.repositories;

import com.svirida.books.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {

}
