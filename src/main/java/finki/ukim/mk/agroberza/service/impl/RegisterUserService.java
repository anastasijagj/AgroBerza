package finki.ukim.mk.agroberza.service.impl;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.repository.MainUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterUserService implements UserDetailsService {
    private final MainUserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findMainUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public String signUpUser(MainUser user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user).toString();
    }
}
