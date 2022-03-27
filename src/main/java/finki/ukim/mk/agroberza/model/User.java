package finki.ukim.mk.agroberza.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @Enumerated(value = EnumType.STRING)
    private userCategory userCategory;

    public User (){

    };

    public User(String username, String password, String name, String surname, userCategory userCategory) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userCategory = userCategory;
    }
}
