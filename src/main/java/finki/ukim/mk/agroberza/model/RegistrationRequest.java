package finki.ukim.mk.agroberza.model;

import finki.ukim.mk.agroberza.model.enums.UserCategory;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String username;
    private String name;
    private String surname;
    private String password;
    private UserCategory userCategory;
}
