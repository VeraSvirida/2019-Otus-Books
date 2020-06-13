package com.svirida.books.repositories;

import com.svirida.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {

}
