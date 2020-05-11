package com.svirida.books.repositories;

import com.svirida.books.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {

}
