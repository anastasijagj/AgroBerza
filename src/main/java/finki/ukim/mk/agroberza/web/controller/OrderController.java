package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.Naracka;
import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.model.enums.Status;
import finki.ukim.mk.agroberza.service.MainUserService;
import finki.ukim.mk.agroberza.service.OrderService;
import finki.ukim.mk.agroberza.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("orders")

public class OrderController {
    private final ProductService productService;
    private final MainUserService mainUserService;
    private final OrderService orderService;

    public OrderController(ProductService productService, MainUserService mainUserService, OrderService orderService) {
        this.productService = productService;
        this.mainUserService = mainUserService;
        this.orderService = orderService;
    }

    @GetMapping
    private String getOrdersForUser(Model model) {
        MainUser currentUser = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        Long userId = currentUser.getId();
        List<Naracka> narackaList = new ArrayList<>();

        narackaList.addAll(this.orderService.findAllByOrderedByUserId(userId));
        Collections.reverse(narackaList);


      /*  for(int i=0;i<narackaList.size();i++)
        {
            narackaList.get(i).brojO=mainUserService.findById(narackaList.get(i).getProducts().get(0).getOwnerId()).orElse(null).number;
            narackaList.get(i).gradO=mainUserService.findById(narackaList.get(i).getProducts().get(0).getOwnerId()).orElse(null).city;
            narackaList.get(i).orderToUserName=mainUserService.findById(narackaList.get(i).getProducts().get(0).getOwnerId()).orElse(null).getName();
        }
*/

        model.addAttribute("orders", narackaList);

        model.addAttribute("bodyContent","orders-page");
        return "master-page";

    }

    @GetMapping("/naracki")
    private String naracki(Model model,@RequestParam(required = false) String error) {

        if(error!=null)
        {
            model.addAttribute("error",true);
        }

        MainUser currentUser = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = currentUser.getId();
        List<Naracka> narackaList = new ArrayList<>();
        narackaList.addAll(this.orderService.findAllByOrderToUserId(userId));
        Collections.reverse(narackaList);

        ArrayList<String> users= new ArrayList<>();

        for(int i=0;i<narackaList.size();i++)
        {
            narackaList.get(i).broj=(mainUserService.findById(narackaList.get(i).getOrderedByUserId()).orElse(null).number);
            narackaList.get(i).grad=(mainUserService.findById(narackaList.get(i).getOrderedByUserId()).orElse(null).city);

            narackaList.get(i).orderedByUserName=(mainUserService.findById(narackaList.get(i).getOrderedByUserId()).orElse(null).getName());
            narackaList.get(i).orderedByUserName+=" " +(mainUserService.findById(narackaList.get(i).getOrderedByUserId()).orElse(null).getSurname());
        }

        model.addAttribute("orders", narackaList);
        model.addAttribute("user", currentUser);
        model.addAttribute("bodyContent","naracki");
        return "master-page";

    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        this.orderService.deleteById(id);
        return "redirect:/orders";
    }

    @PostMapping("/accept/{id}")
    public String acceptOrder(@PathVariable Long id) {
        Naracka order = this.orderService.findById(id).get();
        Product p=order.getProducts().get(0);

        if((p.getQuantity()- order.quantity)<0)
        {
            order.setStatus(Status.FALSE);
            this.orderService.editOrderById(id, order);
            return "redirect:/orders/naracki?error=NO";
        }
        Product product= new Product(p.getId(),p.getName(), p.getPrice(), p.getQuantity()-order.quantity);
        product.img=p.getImg();
        product.description=p.description;
        this.productService.edit(product);
        order.setStatus(Status.TRUE);
        this.orderService.editOrderById(id, order);
        return "redirect:/orders/naracki";
    }

    @PostMapping("/decline/{id}")
    public String declineOrder(@PathVariable Long id) {
        Naracka order = this.orderService.findById(id).get();

        order.setStatus(Status.FALSE);
        this.orderService.editOrderById(id, order);
        return "redirect:/orders/naracki";
    }

    @PostMapping("/delete/product/{productId}/{orderId}")
    public String deleteProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId) {
        Naracka activeOrder = this.orderService.findById(orderId).orElseThrow(() -> new RuntimeException());
        this.orderService.removeProductFromOrder(productId, orderId);
        return "redirect:/orders";
    }

    @GetMapping("/add-product/{productId}/{userId}")
    private String addProductToOrderForUser(@PathVariable Long productId, @PathVariable Long userId, Model model, @RequestParam(required = false) Integer kolicina) {


        Product p=this.productService.findById(productId).orElseThrow(() -> new RuntimeException());;

        Naracka naracka= new Naracka(userId,p.getOwnerId());
        if(kolicina==0)
        {
            kolicina=1;
        }
        naracka.quantity=kolicina;
        naracka.cena=naracka.quantity* p.getPrice();
        naracka.addProductToOrder(p);

        this.orderService.addOrder(naracka);

        return "redirect:/orders";

    }

    @GetMapping("/edit/{productId}/{narackaId}")
    private String editOrder(@PathVariable Long productId, @PathVariable Long narackaId, Model model, @RequestParam(required = false) Integer kolicina) {


        Product p=this.productService.findById(productId).orElseThrow(() -> new RuntimeException());

        Naracka naracka= orderService.findById(narackaId).orElse(null);

        if(kolicina==0)
        {
            kolicina=1;
        }

        naracka.quantity=kolicina;
        naracka.cena=kolicina * p.getPrice();
        naracka.addProductToOrder(p);
        naracka.products= new ArrayList<>();
        naracka.products.add(p);

        this.orderService.editOrderById(narackaId,naracka);

        return "redirect:/orders";

    }
}
