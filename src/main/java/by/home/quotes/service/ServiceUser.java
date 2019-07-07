package by.home.quotes.service;


import by.home.quotes.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ServiceUser extends UserDetailsService {
    User getUsername(String username);
    void save(User user);
    UserDetails loadUserByUsername(String username);
    List<User> getUserAll();
    boolean addUser(User user);

    boolean activateUser(String code);
}
