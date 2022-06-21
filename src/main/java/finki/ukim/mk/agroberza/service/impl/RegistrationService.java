package finki.ukim.mk.agroberza.service.impl;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.RegistrationRequest;
import finki.ukim.mk.agroberza.model.enums.UserCategory;
import finki.ukim.mk.agroberza.service.MainUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final MainUserService mainUserService;
    private final RegisterUserService registerUserService;

    public String register(String name,String username,String surname,String password,UserCategory userCategory) {

        return registerUserService.signUpUser(new MainUser(
            username, password,
            name, surname, userCategory
        ));
    }
}
