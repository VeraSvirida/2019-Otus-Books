package com.svirida.books.repositories;

import com.svirida.books.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    Optional<Comment> getById(long id);

    List<Comment> getAllByBookId(long bookId);

    Comment save(Comment comment);

    void updateCommentById(long id, String comment);

    void deleteById(Long id);

}
