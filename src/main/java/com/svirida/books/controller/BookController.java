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

    @GetMapping("/book")
    public String listBook(Model model) {
        List<BookDto> books = bookRepositoryJpa.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        Book newBook = new Book();
        model.addAttribute("books", books);
        model.addAttribute("newBook", newBook);
        return "listBook";
    }

    @GetMapping("/book/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/book/add")
    public String saveBook(Book book, Model model) {
        Book saved = bookRepositoryJpa.save(book);
        List<BookDto> books = bookRepositoryJpa.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        Book newBook = new Book();
        model.addAttribute("books", books);
        model.addAttribute("newBook", newBook);
        return "listBook";
    }

    @PostMapping("/book/edit")
    public String addBook(Book book, Model model) {
        Book saved = bookRepositoryJpa.save(book);
        model.addAttribute((saved));
        return "editBook";
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") long id, Model model) {
        bookRepositoryJpa.deleteById(id);
        List<BookDto> books = bookRepositoryJpa.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        Book newBook = new Book();
        model.addAttribute("books", books);
        model.addAttribute("newBook", newBook);
        return "listBook";
    }
}
