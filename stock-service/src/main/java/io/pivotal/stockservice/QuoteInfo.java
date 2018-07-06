package io.pivotal.stockservice;


//Quote info is the string version of QuoteRecord, and will be used for json
public class QuoteInfo {

    public final String id;
    public final String symbol;
    public final String price;
    public final String volume;
    public final String date;

    public QuoteInfo(String id, String symbol, String price, String volume, String date) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

}
