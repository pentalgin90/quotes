package by.home.quotes.service;

import by.home.quotes.domain.User;
import by.home.quotes.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserImpl implements ServiceUser {
    private final UserRepo userRepo;

    public ServiceUserImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public User getUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
