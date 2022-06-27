package finki.ukim.mk.agroberza.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model)
    {
        model.addAttribute("bodyContent","home");
        return "master-page";
        }
}
