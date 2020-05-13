package com.svirida.books.repositories;

import com.svirida.books.models.Writer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class WriterRepositoryJpaImpl implements WriterRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Writer> getById(long id) {
        return Optional.ofNullable(em.find(Writer.class, id));
    }
}
