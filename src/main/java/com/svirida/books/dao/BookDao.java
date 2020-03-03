package com.svirida.books.dao;

import com.svirida.books.domain.Book;

import java.util.List;

public interface BookDao {

    int insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    int updateByIb(Book book);

    int count();

}
