package finki.ukim.mk.agroberza.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class MainUser {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @OneToMany(mappedBy = "owner")
    private List<Order> orders;

    @Enumerated(value = EnumType.STRING)
    private userCategory userCategory;

    public MainUser(){

    };

    public MainUser(String username, String password, String name, String surname, userCategory userCategory) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userCategory = userCategory;
    }
}
