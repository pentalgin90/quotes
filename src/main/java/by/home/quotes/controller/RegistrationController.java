package by.home.quotes.controller;

import by.home.quotes.domain.User;
import by.home.quotes.service.ServiceUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        if(!serviceUser.addUser(user)){
            model.put("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = serviceUser.activateUser(code);

        if(isActivated){
            model.addAttribute("message", "User successfully activated");
        }else{
            model.addAttribute("message", "Activated code is not found!");
        }

        return "login";
    }

}
