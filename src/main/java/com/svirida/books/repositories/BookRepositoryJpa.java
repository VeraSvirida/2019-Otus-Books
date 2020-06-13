package com.svirida.books.repositories;

import com.svirida.books.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookRepositoryJpa extends CrudRepository<Book, Long> {

}
