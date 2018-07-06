package io.pivotal.stockservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteRecord, Long> {

    //@Query(value = "SELECT * FROM quotes", nativeQuery = true)
    //List<QuoteRecord> findAll();


    //query database with @Query tag
    @Query(value = "SELECT MAX(price), MIN(price), SUM(volume) FROM quote_record WHERE symbol = :symbol and date(date) = :date", nativeQuery = true)
    String daily(@Param("symbol") String symbol, @Param("date") Date date);

    @Query(value = "SELECT price FROM quote_record WHERE symbol = :symbol and date(date) = :date ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Double closingDay(@Param("symbol") String symbol, @Param("date") Date date);

    @Query(value = "SELECT MAX(price), MIN(price), SUM(volume) FROM quote_record WHERE symbol = :symbol and date BETWEEN :date1 AND :date2", nativeQuery = true)
    String monthly(@Param("symbol") String symbol, @Param("date1") String date1, @Param("date2") String date2);

    @Query(value = "SELECT price FROM quote_record WHERE symbol = :symbol and date BETWEEN :date1 AND :date2 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Double closingMonth(@Param("symbol") String symbol, @Param("date1") String date1, @Param("date2") String date2);

    /*
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
    }*/
}
