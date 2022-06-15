package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.service.MainUserService;
import finki.ukim.mk.agroberza.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MainUserService userService;

    @GetMapping
    public String allProductsPage(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MainUser currentUser = (MainUser) auth.getPrincipal();
//        MainUser currentUser =
//            this.userService.findUserByName(principal.getName()).orElseThrow(() -> new RuntimeException());
        System.out.println("USER INFO: " + currentUser.getId());
        model.addAttribute("user", currentUser);
        return "product-page";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String addProduct(@PathVariable Long id, Model model) {
        Product product = this.productService.findById(id).get();
        model.addAttribute("product", product);
        return "add-product-page";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            model.addAttribute("product", product);
            return "add-page";
        }
        return "error-page";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("bodyContent", "add-product");
        return "add-page";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam(required = false) Long id, @RequestParam String name,
                              @RequestParam Double price, @RequestParam Integer quantity) {
        MainUser user = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (id != null) {
            this.productService.edit(id, name, price, quantity);
        } else {
            this.productService.add(name, price, quantity, user.getId());
        }
        return "redirect:/products";
    }

}
