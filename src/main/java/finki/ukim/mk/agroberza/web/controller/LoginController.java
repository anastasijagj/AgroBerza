package finki.ukim.mk.agroberza.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller

public class LoginController {

    @GetMapping("/login")
    public String login_page(Model model, @RequestParam (required = false) String error) {

        if(error!=null)
        {
            model.addAttribute("error","Incorrect username or password.");
        }

        model.addAttribute("bodyContent","login");
        return "master-page";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
