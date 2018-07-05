package io.pivotal.stockservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

@Repository
public class QuoteRepository  {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<QuoteRecord> rowMapper = (ResultSet rs, int row) -> new QuoteRecord(
            rs.getInt("id"),
            rs.getString("symbol"),
            rs.getDouble("price"),
            rs.getInt("volume"),
            new Date(rs.getTimestamp("dateTraded").getTime())
    );

    public QuoteRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SQL_INSERT = "insert into quotes (symbol, price, volume, dateTraded)" +
            " values (?, ?, ?, ?)";

    public void save(QuoteRecord record){
        jdbcTemplate.update(SQL_INSERT, record.symbol, record.price, record.volume, record.date);
    }

    private final String SQL_FIND_ALL = "select * from quotes";

    public List<QuoteRecord> findAll(){
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

}
