package io.pivotal.stockservice;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuotePresenter quotePresenter;

    @Autowired
    private QuoteRepository quoteRepository;


    //returns all of the stock price info
    @GetMapping
    public List<QuoteInfo> quotes(){
        Iterable<QuoteRecord> records = quoteRepository.findAll();
        List<QuoteInfo> quotes = new ArrayList<>();
        for(QuoteRecord q: records){
            quotes.add(quotePresenter.present(q));
        }

        return quotes;
    }

    @GetMapping("/error")
    public ModelAndView error(){
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView("error", model);
    }

    //Post request to load data form data.txt
    @PostMapping("/load")
    public @ResponseBody
    ResponseEntity<String> load() throws Exception {

        /*
        //creates data.txt to save data to
        File file = new File("src/main/resources/data.txt");
        if(file.createNewFile()){
            //file was created, need to read from URL
            URLReader.read(new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json"));
        } else {
            //file exists, no need to read from URL
            System.out.println("File already exists");
        }*/

        List<QuoteRecord> quotes;
        try{
            ObjectMapper mapper = new ObjectMapper();
            quotes = Arrays.asList(mapper.readValue(new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json"), QuoteRecord[].class));
        }catch (JsonParseException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Data load failed", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Data load failed", HttpStatus.NOT_FOUND);
        }

        quoteRepository.save(quotes);

        return new ResponseEntity<String>("Data loaded", HttpStatus.OK);
    }

    //retrieves stock information for a certain day
    @GetMapping("/{symbol}/{date}")
    public ModelAndView daily(@PathVariable("symbol") String symbol, @PathVariable("date") String date) throws ParseException {

        Date sdf = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        String beginOfTheMonth = date.substring(0, 7) + "-00";
        String endOfTheMonth = date.substring(0,7) + "-31";
        Map<String, Object> model = new HashMap<>();

        try{
            String[] resp = quoteRepository.daily(symbol, sdf).split(",");
            Double closingResp = quoteRepository.closingDay(symbol, sdf);
            String[] monthResp = quoteRepository.monthly(symbol, beginOfTheMonth, endOfTheMonth).split(",");
            Double closingMonth = quoteRepository.closingMonth(symbol, beginOfTheMonth, endOfTheMonth);

            DayRecord dr = new DayRecord(Double.parseDouble(resp[0]), Double.parseDouble(resp[1]), Integer.parseInt(resp[2]), date, symbol);
            DayRecord mr = new DayRecord(Double.parseDouble(monthResp[0]), Double.parseDouble(monthResp[1]), Integer.parseInt(monthResp[2]), beginOfTheMonth, symbol);

            model.put("daily", dr);
            model.put("closing", closingResp);
            model.put("monthly", mr);
            model.put("closingMonthly", closingMonth);


            return new ModelAndView("main", model);
            //return "Max price: " + resp[0] + " Min price: " + resp[1] + " Total volume: " + resp[2] + "\nClosing price for " + date + " " + closingResp + "<br></br>"
                   // + "Max price (monthly): " + monthResp[0] + " Min price (monthly): " + monthResp[1] + " Total volume(monthly): " + monthResp[2];

        } catch (Exception e) {
            return new ModelAndView("error", model);
            //return "No stock price data for " + symbol + " on " + date + ".";
        }
    }
}
