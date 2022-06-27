package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.enums.UserCategory;
import finki.ukim.mk.agroberza.service.MainUserService;
import finki.ukim.mk.agroberza.service.impl.RegistrationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class RegistrationController {
    private RegistrationService registrationService;
    private MainUserService mainUserService;

    public RegistrationController(RegistrationService registrationService, MainUserService mainUserService) {
        this.registrationService = registrationService;
        this.mainUserService = mainUserService;
    }

    @GetMapping("/register")
    public String register_page(Model model) {


        model.addAttribute("bodyContent","register");
        return "master-page";
    }


    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String surname,
                           @RequestParam UserCategory userCategory) {
        // System.out.println(userCategory.toString());
        if (this.mainUserService.findUserByName(username).isPresent()) {
            throw new RuntimeException();
        } else {
            this.registrationService.register(name, username, surname, password, userCategory);
            return "redirect:/products";
        }
    }
}
