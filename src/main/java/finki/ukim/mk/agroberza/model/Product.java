package finki.ukim.mk.agroberza.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import lombok.Getter;

@Data
@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToMany
    private List<Order> orders;

    public Product(Long id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product (){};
}
