package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.service.ProductService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String allProductsPage(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        return "product-page";
    }

    @DeleteMapping("/delete/{id}")
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
    public String saveProduct(@RequestParam(required = false) Long id, @RequestParam String name, @RequestParam Double price, @RequestParam Integer quantity) {
        if (id != null) {
            this.productService.edit(id, name, price, quantity);
        } else {
            this.productService.add(name, price, quantity);
        }
        return "redirect:/products";
    }

}
