package finki.ukim.mk.agroberza.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "product")
@NoArgsConstructor
@Getter
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long ownerId;

    private Double price;

    private Integer quantity;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Naracka> narackas = new ArrayList<>();

    public Product(Long id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, Double price, Integer quantity, Long ownerId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.ownerId = ownerId;
    }
}
