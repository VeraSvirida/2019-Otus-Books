package com.svirida.books.repositories;

import com.svirida.books.models.Book;
import com.svirida.books.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepositoryJpa extends MongoRepository<Comment, Long> {

    public List<Comment> findByBook(Book book);

}
