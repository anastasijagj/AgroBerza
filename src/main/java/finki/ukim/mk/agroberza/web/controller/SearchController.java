package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String search) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MainUser currentUser = (MainUser) auth.getPrincipal();

        List<Product> products = this.productService.findProductByName(search);
        model.addAttribute("products", products);
//        MainUser currentUser =
//            this.userService.findUserByName(principal.getName()).orElseThrow(() -> new RuntimeException());
        System.out.println("USER INFO: " + currentUser.getId());
        model.addAttribute("user", currentUser);

        model.addAttribute("bodyContent","product-page");
        return "master-page";
    }

}
