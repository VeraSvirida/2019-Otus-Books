package com.svirida.books.rest;

import com.svirida.books.dto.BookDto;
import com.svirida.books.exception.NotFoundException;
import com.svirida.books.models.Book;
import com.svirida.books.repositories.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookRestController {
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    public BookRestController(BookRepositoryJpa bookRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @GetMapping("/api/books")
    public List<BookDto> listBook(Model model) {
        return bookRepositoryJpa.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/books/edit")
    public Book editBook(@RequestParam("id") long id, Model model) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return book;
    }

    @PostMapping(value= "/api/books/add")
    public Book saveBook(@RequestBody BookDto bookDto) {
        Book saved = BookDto.toDomainObject(bookDto);
        bookRepositoryJpa.save(saved);
        return saved;
    }

//    @PostMapping("/book/edit")
//    public String addBook(Book book, Model model) {
//        Book saved = bookRepositoryJpa.save(book);
//        model.addAttribute((saved));
//        return "editBook";
//    }
//
//    @GetMapping("/book/delete")
//    public String deleteBook(@RequestParam("id") long id, Model model) {
//        bookRepositoryJpa.deleteById(id);
//        List<BookDto> books = bookRepositoryJpa.findAll().stream()
//                .map(BookDto::toDto)
//                .collect(Collectors.toList());
//        Book newBook = new Book();
//        model.addAttribute("books", books);
//        model.addAttribute("newBook", newBook);
//        return "listBook";
//    }
}
