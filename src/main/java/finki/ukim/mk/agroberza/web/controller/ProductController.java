package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String allProductsPage(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        return "product-page";
    }
}
