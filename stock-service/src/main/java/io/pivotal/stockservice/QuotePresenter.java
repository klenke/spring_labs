package io.pivotal.stockservice;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class QuotePresenter {

    //DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public QuoteInfo present(QuoteRecord record){
        return new QuoteInfo(
                Integer.toString(record.id),
                record.symbol,
                Double.toString(record.price),
                Integer.toString(record.volume),
                record.date.toString()
        );
    }
}
