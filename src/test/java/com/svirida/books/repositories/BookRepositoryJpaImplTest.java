package com.svirida.books.repositories;

import com.svirida.books.models.Book;
import com.svirida.books.models.Genre;
import com.svirida.books.models.Writer;
import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
//Понимаю что тестировать Spring Data Jpa нет смысла, но оставлю пока эти тесты здесь.
@DisplayName("Репозиторий на основе Jpa для работы с книгами")
@DataJpaTest
@Import(BookRepositoryJpa.class)
public class BookRepositoryJpaImplTest {

    private static final long FIRST_BOOK_ID = 3;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 6;
    private static final int EXPECTED_QUERIES_COUNT = 13;
    private static final String BOOK_TITLE = "Title";
    private static final String BOOK_DESC = "Description";

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен загружать информацию о книге по Id")
    @Test
    void shouldFindExpectedStudentById() {
        val optionalActualBook = repositoryJpa.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName("Должен загружать список всех книг")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);


        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val students = repositoryJpa.findAll();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(s -> !s.getDescription().equals(""))
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getWriter() != null)
                .allMatch(s -> s.getGenre() != null);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName("Должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveAllStudentInfo() {
        val genre = new Genre(7, "genre7");
        val writer = new Writer(7, "Writer7", "30.10.1989");

        val book = new Book(7, BOOK_TITLE, BOOK_DESC, genre, writer,null);
        repositoryJpa.save(book);
        assertThat(book.getId()).isGreaterThan(0);

        val actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(s -> s.getTitle().equals(BOOK_TITLE))
                .matches(s -> s.getDescription().equals(BOOK_DESC))
                .matches(s -> s.getGenre() != null)
                .matches(s -> s.getWriter() != null);
    }

    @DisplayName("Должен удалять книгу по id")
    @Test
    void shouldDeleteStudentNameById() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        repositoryJpa.deleteById(FIRST_BOOK_ID);
        val deletedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(deletedBook).isNull();
    }

}
