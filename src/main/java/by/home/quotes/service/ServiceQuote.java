package by.home.quotes.service;

import by.home.quotes.domain.Quote;

import java.util.Optional;

public interface ServiceQuote {

    Iterable<Quote> getAll();

    Optional<Quote> getOneQuote(Long id);

    void save(Quote quote);

    void deleteQuote(Quote quote);

    Iterable<Quote> findByTag(String tag);
}
