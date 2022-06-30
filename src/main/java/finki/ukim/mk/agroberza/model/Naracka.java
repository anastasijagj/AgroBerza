package finki.ukim.mk.agroberza.model;



import finki.ukim.mk.agroberza.model.enums.AppUserRole;
import finki.ukim.mk.agroberza.model.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "narackaa")

public class Naracka implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderedByUserId;
    public String orderedByUserName;
    public String broj;
    public String grad;

    public String brojO;
    public String gradO;
    public String orderToUserName;
    private Long orderToUserId;
    public Integer quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "naracka_product",
//        joinColumns = {@JoinColumn(name = "naracka_id")},
//        inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products = new ArrayList<>();

    public void addProductToOrder(Product product) {
        this.products.add(product);
    }

    public void addProductsToOrder(List<Product> products) {
        for (Product p : products) {
            this.products.add(p);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderedByUserId(Long orderedByUserId) {
        this.orderedByUserId = orderedByUserId;
    }

    public void setOrderToUserId(Long orderToUserId) {
        this.orderToUserId = orderToUserId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Naracka() {
    }

    public Long getId() {
        return id;
    }

    public Long getOrderedByUserId() {
        return orderedByUserId;
    }

    public Long getOrderToUserId() {
        return orderToUserId;
    }



    public List<Product> getProducts() {
        return products;
    }

    public void removeAllProductsFromOrder() {
        this.products.clear();
    }

    public Naracka(Long orderedByUserId, Long orderToUserId) {
        this.orderedByUserId = orderedByUserId;
        this.orderToUserId = orderToUserId;
    }


}
