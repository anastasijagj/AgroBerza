package finki.ukim.mk.agroberza.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "product")
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long ownerId;

    private Double price;

    private Integer quantity;

    @ManyToMany(mappedBy = "products")
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
