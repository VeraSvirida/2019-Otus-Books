package com.svirida.books.shell;

import com.svirida.books.models.Book;
import com.svirida.books.models.Comment;
import com.svirida.books.repositories.BookRepositoryJpa;
import com.svirida.books.repositories.CommentRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommand {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private final BookRepositoryJpa bookRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;


    //Показать по Id
    @ShellMethod(value = "getBookByIdBook", key = {"bGetById"})
    public void getById(@ShellOption(defaultValue = "1") long id) {
        Optional<Book> book = bookRepositoryJpa.findById(id);
        System.out.println(book.toString());
    }

    // получить все
    @ShellMethod(value = "getAllBook", key = {"bGetAll"})
    public void getAll() {
        for (Book allBook : bookRepositoryJpa.findAll()) {
            System.out.println(allBook.toString());
        }
    }

    //Добавить новый
    @ShellMethod(value = "addBook", key = {"bAdd"})
    public void add(@ShellOption Long id, @ShellOption String title, @ShellOption String description, @ShellOption String genre, @ShellOption String writer) {
        Book bookInsert = new Book(id, title, description, genre, writer, null);
        bookRepositoryJpa.save(bookInsert);
    }

    //Удалить
    @Transactional
    @ShellMethod(value = "deleteBook", key = {"bDel"})
    public void delete(@ShellOption long id) {
        bookRepositoryJpa.deleteById(id);
    }

    //Обновить
    @ShellMethod(value = "updateDescriptionOfBook", key = {"bUpd"})
    public void update(@ShellOption long id, @ShellOption String description) {
        Optional<Book> updBook = bookRepositoryJpa.findById(id);
        if (updBook.isPresent()) {
            updBook.get().setDescription(description);
            bookRepositoryJpa.save(updBook.get());
        }
    }

    //Показать все комментарии по книге
    @Transactional
    @ShellMethod(value = "getAllCommentByIdBook", key = {"cGetAll"})
    public void showComment(@ShellOption long id) {
        Optional<Book> optionalBook = bookRepositoryJpa.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            for (Comment comment : commentRepositoryJpa.findByBook(book)) {
                System.out.println(comment);
            }
        }
    }

    //Добавить комментарий к книге
    @ShellMethod(value = "addComment", key = {"cAdd"})
    public void addComment(@ShellOption Long id, @ShellOption String comment, @ShellOption long bookId) {
        final Optional<Book> optionalBook = bookRepositoryJpa.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            final Comment newComment = new Comment(id, comment, book);
            commentRepositoryJpa.save(newComment);
        }
    }

    //Удалить комментарий к книге
    @ShellMethod(value = "deleteComment", key = {"cDel"})
    public void deleteComment(@ShellOption long id) {
        commentRepositoryJpa.deleteById(id);
    }
}
