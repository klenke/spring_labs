package io.pivotal.stockservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteRecord, Long> {

    //query database with @Query tag
    @Query(value = "SELECT MAX(price), MIN(price), SUM(volume) FROM quote_record WHERE symbol = :symbol and date(date) = :date", nativeQuery = true)
    String daily(@Param("symbol") String symbol, @Param("date") Date date);

    @Query(value = "SELECT price FROM quote_record WHERE symbol = :symbol and date(date) = :date ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Double closingDay(@Param("symbol") String symbol, @Param("date") Date date);

    @Query(value = "SELECT MAX(price), MIN(price), SUM(volume) FROM quote_record WHERE symbol = :symbol and date BETWEEN :date1 AND :date2", nativeQuery = true)
    String monthly(@Param("symbol") String symbol, @Param("date1") String date1, @Param("date2") String date2);

    @Query(value = "SELECT price FROM quote_record WHERE symbol = :symbol and date BETWEEN :date1 AND :date2 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Double closingMonth(@Param("symbol") String symbol, @Param("date1") String date1, @Param("date2") String date2);

}
