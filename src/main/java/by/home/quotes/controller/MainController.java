package by.home.quotes.controller;

import by.home.quotes.domain.Quote;
import by.home.quotes.service.ServiceQuote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private final ServiceQuote serviceQuote;

    public MainController(ServiceQuote serviceQuote) {
        this.serviceQuote = serviceQuote;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Quote> quotes = serviceQuote.getAll();
        model.put("quotes", quotes);
        return "main";
    }

    @PostMapping("/main")
    public String addQuotes(@RequestParam String text,
                            @RequestParam String tag,
                            Map<String, Object> model){
        Quote quote = new Quote(text, tag);
        serviceQuote.save(quote);
        Iterable<Quote> quotes = serviceQuote.getAll();
        model.put("quotes", quotes);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String tag,
                         Map<String, Object> model){
        Iterable<Quote> quotes = serviceQuote.findByTag(tag);
        model.put("quotes", quotes);
        return "main";
    }
}
