package by.home.quotes.service;

import by.home.quotes.domain.Role;
import by.home.quotes.domain.User;
import by.home.quotes.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    public void save(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key: form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
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

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if(!user.getEmail().isEmpty()){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcom to Quotes. Please, visit next link: https://heroku-localmems.herokuapp.com/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
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

    @Override
    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChange = (email != null && !email.equals(userEmail) ||
                                (userEmail != null && !userEmail.equals(email)));
        if(isEmailChange){
            user.setEmail(email);
            if(StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if(StringUtils.isEmpty(password)){
            user.setPassword(password);
        }
        userRepo.save(user);
        if(isEmailChange){
            sendMessage(user);
        }
    }

}
