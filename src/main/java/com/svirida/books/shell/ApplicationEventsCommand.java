package com.svirida.books.shell;

import com.svirida.books.dao.BookDao;
import com.svirida.books.dao.GenreDao;
import com.svirida.books.dao.WriterDao;
import com.svirida.books.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommand {

    private final BookDao dao;
    private final GenreDao genreDao;
    private final WriterDao writerDao;

    //Показать по Id
    @ShellMethod(value = "getByIdBook", key = {"gBI"})
    public void getById(@ShellOption(defaultValue = "1") int id) {
        Book book = dao.getById(id);
        System.out.println(book.toString());
    }

    // получить все
    @ShellMethod(value = "getAllBook", key = {"gA"})
    public void getAll() {
        for (Book allBook : dao.getAll()) {
            System.out.println(allBook);
        }
    }

    //Добавить новый
    @ShellMethod(value = "addBook", key = {"add"})
    public void add(@ShellOption Long id, @ShellOption String title, @ShellOption String description, @ShellOption Long genreId, @ShellOption Long writerId) {
        Book bookInsert = new Book(id, title, description, genreDao.getById(genreId), writerDao.getById(writerId));
        dao.insert(bookInsert);
    }

    //Удалить
    @ShellMethod(value = "deleteBook", key = {"del", "d"})
    public void delete(@ShellOption int id) {
        dao.deleteById(id);
    }

    //Обновить
    @ShellMethod(value = "updateBook", key = {"upd", "u"})
    public void update(@ShellOption int id, @ShellOption String title, @ShellOption String description, @ShellOption Long genreId, @ShellOption Long writerId) {
        Book bookUpdate = dao.getById(id);
        bookUpdate.setTitle(title);
        bookUpdate.setDescription(description);
        bookUpdate.setGenre(genreDao.getById(genreId));
        bookUpdate.setWriter(writerDao.getById(writerId));
        dao.updateByIb(bookUpdate);
    }

}
