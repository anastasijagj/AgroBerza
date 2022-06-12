package finki.ukim.mk.agroberza.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "naracka")
@NoArgsConstructor
@Getter
public class Naracka implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderedByUserId;
    private Long orderToUserId;

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
    public void removeAllProductsFromOrder(){
        this.products.clear();
    }

    public Naracka(Long orderedByUserId, Long orderToUserId) {
        this.orderedByUserId = orderedByUserId;
        this.orderToUserId = orderToUserId;
    }

}
