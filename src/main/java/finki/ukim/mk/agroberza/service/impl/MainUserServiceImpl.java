package finki.ukim.mk.agroberza.service.impl;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.repository.MainUserRepository;
import finki.ukim.mk.agroberza.service.MainUserService;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MainUserServiceImpl implements MainUserService {
    MainUserRepository mainUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MainUserServiceImpl(MainUserRepository mainUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mainUserRepository = mainUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<MainUser> findAll() {
        return this.mainUserRepository.findAll();
    }

    @Override
    public Optional<MainUser> findById(Long id) {
        return this.mainUserRepository.findById(id);
    }

    @Override
    public Optional<MainUser> findUserByName(String name) {
        return this.mainUserRepository.findMainUserByName(name);
    }
}
