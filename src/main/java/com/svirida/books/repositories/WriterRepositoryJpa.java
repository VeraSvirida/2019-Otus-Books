package com.svirida.books.repositories;

import com.svirida.books.models.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepositoryJpa extends JpaRepository<Writer, Long> {

}
