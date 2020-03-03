package com.svirida.books.dao;

import com.svirida.books.domain.Book;
import com.svirida.books.domain.Genre;
import com.svirida.books.domain.Writer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookDaoImpl implements BookDao {

    private static GenreDao genreDao;
    private static WriterDao writerDao;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoImpl(GenreDao genreDao, WriterDao writerDao, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.genreDao = genreDao;
        this.writerDao = writerDao;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert(Book book) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        params.put("description", book.getDescription());
        params.put("genreId", book.getGenre().getId());
        params.put("writerId", book.getWriter().getId());

        return namedParameterJdbcOperations.update(
                "insert into books (id, title, description, genre_id, writer_id) values (:id, :title, :description, :genreId, :writerId )", params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from books where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {

        return namedParameterJdbcOperations.query("select * from books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );

    }

    @Override
    public int updateByIb(Book book) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        params.put("description", book.getDescription());
        params.put("genreId", book.getGenre().getId());
        params.put("writerId", book.getWriter().getId());


        return namedParameterJdbcOperations.update(
                "update books set title=:title, description=:description, genre_id=:genreId, writer_id=:writerId where id = :id", params);

    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);

    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            Genre genre = genreDao.getById(resultSet.getLong("genre_id"));
            Writer writer = writerDao.getById(resultSet.getLong("writer_id"));
            return new Book(id, title, description, genre, writer);
        }
    }
}
