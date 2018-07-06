package io.pivotal.stockservice;

import org.springframework.stereotype.Component;


@Component
public class QuotePresenter {

    //quote presenter is used to convery QuoteRecord objects to their string equivalent
    public QuoteInfo present(QuoteRecord record){
        return new QuoteInfo(
                Long.toString(record.id),
                record.symbol,
                Double.toString(record.price),
                Integer.toString(record.volume),
                record.date.toString()
        );
    }
}
