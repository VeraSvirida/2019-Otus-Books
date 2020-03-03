package com.svirida.books.dao;

import com.svirida.books.domain.Writer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Service
public class WriterDaoImpl implements WriterDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public WriterDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Writer getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from writers where id = :id", params, new WriterMapper()
        );
    }

    private static class WriterMapper implements RowMapper<Writer> {
        @Override
        public Writer mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String fullName = resultSet.getString("fullname");
            String birthday = resultSet.getString("birthday");
            return new Writer(id, fullName, birthday);
        }
    }
}
