package finki.ukim.mk.agroberza.model;

import finki.ukim.mk.agroberza.model.enums.AppUserRole;
import finki.ukim.mk.agroberza.model.enums.UserCategory;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "main_user")
public class MainUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private UserCategory userCategory;

    public MainUser(String username, String password, String name, String surname, UserCategory userCategory) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userCategory = userCategory;
        this.enabled = true;
        this.locked = false;
        if (userCategory.equals(UserCategory.FARMER)) {
            this.appUserRole = AppUserRole.ADMIN;
        } else {
            this.appUserRole = AppUserRole.USER;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
