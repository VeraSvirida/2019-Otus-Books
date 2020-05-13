package com.svirida.books.repositories;

import com.svirida.books.models.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }
    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Book delBook = em.find(Book.class, id);
        em.remove(delBook);
    }

    @Transactional
    @Override
    public void updateDescriptionByIb(long id, String description) {
        Book updBook = em.find(Book.class, id);
        updBook.setDescription(description);
        em.merge(updBook);
    }
}
