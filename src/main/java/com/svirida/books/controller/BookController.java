package com.svirida.books.controller;

import com.svirida.books.dto.BookDto;
import com.svirida.books.exception.NotFoundException;
import com.svirida.books.models.Book;
import com.svirida.books.repositories.BookRepositoryJpa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private BookRepositoryJpa bookRepositoryJpa;

    public BookController(BookRepositoryJpa bookRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @GetMapping("/")
    public String listBook(Model model) {
        return "listBook";
    }

    @GetMapping("/book/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        return "editBook";
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") long id, Model model) {
        return "listBook";
    }
}
