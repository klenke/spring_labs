package io.pivotal.stockservice;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteRepository quoteRepository;
    private final QuotePresenter quotePresenter;

    public QuoteController(QuoteRepository quoteRepository, QuotePresenter quotePresenter) {
        this.quoteRepository = quoteRepository;
        this.quotePresenter = quotePresenter;
    }

    @GetMapping
    public List<QuoteInfo> quotes(){
        return quoteRepository.findAll()
                .stream()
                .map(quotePresenter::present)
                .collect(Collectors.toList());
    }

    @GetMapping("/load")
    public String load() {
        List<QuoteRecord> quotes;
        try{
            ObjectMapper mapper = new ObjectMapper();
            quotes = Arrays.asList(mapper.readValue(new File("src/main/resources/data.txt"), QuoteRecord[].class));
        }catch (JsonParseException e) {
            e.printStackTrace();
            return "Data load failed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Data load failed";
        }

        for(QuoteRecord q: quotes){
            quoteRepository.save(q);
        }

        return "Data loaded!";
    }

    @GetMapping("/{symbol}/{date}")
    public String daily(@PathVariable("symbol") String symbol, @PathVariable("date") String date){



        return "daily aggregation failed";
    }
}
