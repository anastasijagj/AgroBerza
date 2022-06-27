package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.service.MainUserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")

public class ProfileController {
    private final MainUserService userService;

    public ProfileController(MainUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MainUser currentUser = (MainUser) auth.getPrincipal();
        MainUser user = this.userService.findById(currentUser.getId()).orElseThrow(() -> new RuntimeException());
        model.addAttribute("user", user);
        model.addAttribute("bodyContent","profile-page");
        return "master-page";

    }
}
