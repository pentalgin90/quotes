package by.home.quotes.service;

import by.home.quotes.domain.Role;
import by.home.quotes.domain.User;
import by.home.quotes.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceUserImpl implements ServiceUser {
    private final UserRepo userRepo;
    private final MailSender mailSender;

    public ServiceUserImpl(UserRepo userRepo, MailSender mailSender){
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public User getUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> getUserAll() {
        return userRepo.findAll();
    }

    public boolean addUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if(!user.getEmail().isEmpty()){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcom to Quotes. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    @Override
    public boolean activateUser(String code) {
       User user = userRepo.findByActivationCode(code);
       if(user == null){
           return false;
       }

        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }

}
