package by.home.quotes.controller;

import by.home.quotes.domain.Quote;
import by.home.quotes.domain.User;
import by.home.quotes.service.ServiceQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private ServiceQuote serviceQuote;

    @GetMapping("/main")
    public String greeting() {
        return "greeting";
    }
    @GetMapping("/")
    public String main(Model model,
                       @RequestParam(required = false, defaultValue = "") String tag){
        Iterable<Quote> quotes = serviceQuote.findByTag(tag);
        model.addAttribute("quotes", quotes);
        model.addAttribute("tag", tag);
        return "main";
    }

    @PostMapping("/")
    public String addQuotes(
            @AuthenticationPrincipal User user,
            @Valid Quote quote,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        quote.setAuthor(user);

        if(bindingResult.hasErrors()){

            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("quote", quote);

        } else {

            if(file != null && !file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);

                if(!uploadDir.exists()){
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName));

                quote.setFilename(resultFileName);
            }

            model.addAttribute("quote", null);

            serviceQuote.save(quote);

        }
        Iterable<Quote> quotes = serviceQuote.getAll();
        model.addAttribute("quotes", quotes);
        return "main";
    }

}
