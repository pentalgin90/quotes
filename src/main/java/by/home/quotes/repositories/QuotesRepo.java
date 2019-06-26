package by.home.quotes.repositories;

import by.home.quotes.domain.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuotesRepo extends CrudRepository<Quote, Long> {
}
