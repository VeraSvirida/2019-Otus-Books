package com.svirida.books.shell;

import com.svirida.books.models.Book;
import com.svirida.books.models.Comment;
import com.svirida.books.repositories.BookRepositoryJpa;
import com.svirida.books.repositories.CommentRepositoryJpa;
import com.svirida.books.repositories.GenreRepositoryJpa;
import com.svirida.books.repositories.WriterRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommand {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final WriterRepositoryJpa writerRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;


    //Показать по Id
    @ShellMethod(value = "getBookByIdBook", key = {"bGetById"})
    public void getById(@ShellOption(defaultValue = "1") int id) {
        Optional<Book> book = bookRepositoryJpa.getById(id);
        System.out.println(book.toString());
    }

    // получить все
    @ShellMethod(value = "getAllBook", key = {"bGetAll"})
    public void getAll() {
        for (Book allBook : bookRepositoryJpa.getAll()) {
            System.out.println(allBook.toString());
        }
    }

    //Добавить новый
    @ShellMethod(value = "addBook", key = {"bAdd"})
    public void add(@ShellOption Long id, @ShellOption String title, @ShellOption String description, @ShellOption Long genreId, @ShellOption Long writerId) {
        Book bookInsert = new Book(id, title, description, genreRepositoryJpa.getById(genreId).get(), writerRepositoryJpa.getById(writerId).get(), null);
        bookRepositoryJpa.save(bookInsert);
    }

    //Удалить
    @ShellMethod(value = "deleteBook", key = {"bDel"})
    public void delete(@ShellOption long id) {
        bookRepositoryJpa.deleteById(id);
    }

    //Обновить
    @ShellMethod(value = "updateDescriptionOfBook", key = {"bUpd"})
    public void update(@ShellOption int id, @ShellOption String description) {
        bookRepositoryJpa.updateDescriptionByIb(id, description);
    }

    //Показать все комментарии по книге
    @ShellMethod(value = "getAllCommentByIdBook", key = {"cGetAll"})
    public void showComment(@ShellOption int id) {
        Optional<Book> book = bookRepositoryJpa.getById(id);
        if (book.isPresent()) {
            for (Comment comment : book.get().getComment()) {
                System.out.println(comment.toString());
            }
        }
    }

    //Добавить комментарий к книге
    @ShellMethod(value = "addComment", key = {"cAdd"})
    public void addComment(@ShellOption int id, @ShellOption String comment, @ShellOption int bookId) {
        Comment newComment = new Comment(id, comment, bookRepositoryJpa.getById(bookId).get());
        commentRepositoryJpa.save(newComment);
    }

    //Удалить комментарий к книге
    @ShellMethod(value = "deleteComment", key = {"cDel"})
    public void deleteComment(@ShellOption long id) {
        commentRepositoryJpa.deleteById(id);
    }
}
