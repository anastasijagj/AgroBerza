package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.Naracka;
import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.service.MainUserService;
import finki.ukim.mk.agroberza.service.OrderService;
import finki.ukim.mk.agroberza.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("narackas")
@AllArgsConstructor
public class OrderController {
    private final ProductService productService;
    private final MainUserService mainUserService;
    private final OrderService orderService;

    @GetMapping("/user")
    private String getOrdersForUser(Model model) {
        MainUser currentUser = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = currentUser.getId();
        List<Naracka> narackaList = this.orderService.findAllByOrderedByUserId(userId);

        model.addAttribute("orders", narackaList);
        return "order-page";
    }

    @GetMapping("/add-product/{productId}/{userId}")
    private String addProductToOrderForUser(@PathVariable Long productId, @PathVariable Long userId, Model model) {
        //TODO: change exceptions to custom ones
        List<Naracka> narackas = this.orderService.findAllByOrderedByUserId(userId);
        boolean productAdded = false;
        if (narackas.isEmpty()) {
            narackas = new ArrayList<>();
        }
        Product product = this.productService.findById(productId).orElseThrow(() -> new RuntimeException());
        MainUser currentUser = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Naracka naracka = new Naracka(currentUser.getId(), product.getOwnerId());
        naracka.addProductToOrder(product);

        for (Naracka narackaInList : narackas) {
            if (narackaInList.getOrderedByUserId().equals(naracka.getOrderedByUserId())
                && narackaInList.getOrderToUserId().equals(naracka.getOrderToUserId())) {
                narackaInList.addProductsToOrder(naracka.getProducts());
                productAdded = true;
            }
        }
        if (!productAdded) {
            this.orderService.addOrder(naracka);
        }

        List<Naracka> narackaList = this.orderService.findAllByOrderedByUserId(userId);
        model.addAttribute("orders", narackaList);
        return "order-page";
    }
}
