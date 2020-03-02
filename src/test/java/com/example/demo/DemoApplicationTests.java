package com.example.demo;

import com.svirida.books.Main;
import com.svirida.books.dao.BookDao;
import com.svirida.books.dao.GenreDao;
import com.svirida.books.dao.WriterDao;
import com.svirida.books.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест BookDaoImpl")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, properties = {
		InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})

class DemoApplicationTests {
    @Autowired
    private BookDao bookDao;
	@Autowired
    private GenreDao genreDao;
	@Autowired
    private WriterDao writerDao;

    private static int countBook = 3;
    private static long bookId = 4;

    @DisplayName("Количество книг (count)")
    @Test
    void shouldReturnCountBooksInDatabase() {
        assertThat(bookDao.count() == countBook);

    }

	@DisplayName("Вывести все книги (getAll)")
	@Test
	void shouldReturnAllBook() {
		assertThat(bookDao.getAll().size()==countBook);
	}

	@DisplayName("Добавить новую книгу в БД")
	@Test
	void shouldInsertBookInDb() {
		int insertedBook = bookDao.insert(new Book(4, "New Book", "Description of new book", genreDao.getById(1), writerDao.getById(1)));
		assertThat(bookDao.count()==countBook + 1);
		bookDao.deleteById(bookId);
	}

	@DisplayName("Обновить книгу в БД")
	@Test
	void shouldUpdateBookInDb() {
		Book updatedBook = bookDao.getById(1);
		updatedBook.setDescription("New description");
		bookDao.updateByIb(updatedBook);
		assertThat(bookDao.getById(1).equals(updatedBook));
	}

}
