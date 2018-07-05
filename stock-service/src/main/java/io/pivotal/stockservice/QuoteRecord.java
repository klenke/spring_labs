package io.pivotal.stockservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Date;

public class QuoteRecord {

    @JsonIgnore
    public int id;

    public String symbol;
    public double price;
    public int volume;
    public Date date;

    public QuoteRecord(){}

    public QuoteRecord(int id, String symbol, double price, int volume, Date date) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }


}
