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
@RequestMapping("orders")
@AllArgsConstructor
public class OrderController {
    private final ProductService productService;
    private final MainUserService mainUserService;
    private final OrderService orderService;

    @GetMapping("")
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
        //get all orders for the current user
        List<Naracka> narackas = this.orderService.findAllByOrderedByUserId(userId);
        boolean productAdded = false;
        if (narackas.isEmpty()) {
            narackas = new ArrayList<>();
        }
        //get product that the user ordered
        Product product = this.productService.findById(productId).orElseThrow(() -> new RuntimeException());
        MainUser currentUser = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //create new order for this user
        Naracka naracka = new Naracka(currentUser.getId(), product.getOwnerId());
        naracka.addProductToOrder(product);

        //pomini site naracki za current user
        for (Naracka narackaInList : narackas) {
            //ako postoi veke naracka pomegju istiot owner i current user
            if (narackaInList.getOrderedByUserId().equals(naracka.getOrderedByUserId())
                && narackaInList.getOrderToUserId().equals(naracka.getOrderToUserId())) {
                //dodadi produkt na taa naracka
                narackaInList.addProductsToOrder(naracka.getProducts());
                narackas.remove(narackaInList);
                narackas.add(narackaInList);
                productAdded = true;
            }
        }
        //ako nema naracka pomegju current user i owner na toj produkt
        if (!productAdded) {
            //dodadi ja novata naracka
            this.orderService.addOrder(naracka);
        }

        //zemi gi site naracki za current user
        List<Naracka> narackaList = this.orderService.findAllByOrderedByUserId(currentUser.getId());
        System.out.println("NARACKA:" + narackaList.size());
        model.addAttribute("orders", narackaList);
        model.addAttribute("korisnik", currentUser);
        return "order-page";
    }
}
