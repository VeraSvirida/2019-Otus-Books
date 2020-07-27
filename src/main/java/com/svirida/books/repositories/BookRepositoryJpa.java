package com.svirida.books.repositories;

import com.svirida.books.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookRepositoryJpa extends PagingAndSortingRepository<Book, Long> {

    List<Book> findAll();
}
