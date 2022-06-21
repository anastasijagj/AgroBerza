package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.RegistrationRequest;
import finki.ukim.mk.agroberza.model.enums.UserCategory;
import finki.ukim.mk.agroberza.service.impl.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String register_page() {
        return "register";
    }


    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String surname,
                           @RequestParam UserCategory userCategory) {
       // System.out.println(userCategory.toString());
         this.registrationService.register(name,username,surname,password,userCategory);
        return "redirect:/products";
    }
}
