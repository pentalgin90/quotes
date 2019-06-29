package by.home.quotes.service;

import by.home.quotes.domain.Quote;
import by.home.quotes.repositories.QuotesRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceQuoteImpl implements ServiceQuote{

    private final QuotesRepo quotesRepo;

    public ServiceQuoteImpl(QuotesRepo quotesRepo) {
        this.quotesRepo = quotesRepo;
    }

    @Override
    public Iterable<Quote> getAll() {
        return quotesRepo.findAll();
    }

    @Override
    public Optional<Quote> getOneQuote(Long id) {
        return quotesRepo.findById(id);
    }

    @Override
    public void save(Quote quote) {
        quotesRepo.save(quote);
    }

    @Override
    public void deleteQuote(Quote quote) {
        quotesRepo.delete(quote);
    }

    @Override
    public Iterable<Quote> findByTag(String tag) {
        if (tag != null && !tag.isEmpty()) {
            return quotesRepo.findByTag(tag);
        } else {
            return quotesRepo.findAll();
        }
    }
}
