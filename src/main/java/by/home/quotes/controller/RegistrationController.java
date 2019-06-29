package by.home.quotes.controller;
import by.home.quotes.domain.Role;
import by.home.quotes.domain.User;
import by.home.quotes.service.ServiceUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final ServiceUser serviceUser;

    public RegistrationController(ServiceUser serviceUser){
        this.serviceUser = serviceUser;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = serviceUser.getUsername(user.getUsername());
        if(userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        return "redirect:/login";
    }

}
