package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.MainUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String homePage(Model model)
    {

       if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser"))
       {
           model.addAttribute("bodyContent","home");
           return "master-page";
       }

        MainUser currentUser = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        model.addAttribute("bodyContent","home");
        return "master-page";
        }
}
