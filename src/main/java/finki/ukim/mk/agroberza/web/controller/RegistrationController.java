package finki.ukim.mk.agroberza.web.controller;

import finki.ukim.mk.agroberza.model.RegistrationRequest;
import finki.ukim.mk.agroberza.service.impl.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return this.registrationService.register(request);
    }
}
