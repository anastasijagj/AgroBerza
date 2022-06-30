package finki.ukim.mk.agroberza.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "product")

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public String img;

    public String description;

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

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<Naracka> getNarackas() {
        return narackas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setNarackas(List<Naracka> narackas) {
        this.narackas = narackas;
    }
}
