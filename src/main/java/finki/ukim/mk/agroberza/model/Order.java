package finki.ukim.mk.agroberza.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    // @JoinColumn(name = "user_id", nullable = false)
    private MainUser user;

    @ManyToMany
    private List<Product> products;

    public Order() {
    }

    public Order(String name, MainUser user) {
        this.name = name;
        this.user = user;
    }

}
