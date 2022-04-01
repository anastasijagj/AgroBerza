package finki.ukim.mk.agroberza.service.impl;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.repository.MainUserRepository;
import finki.ukim.mk.agroberza.service.MainUserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class MainUserServiceImpl implements MainUserService {
    @Autowired
    MainUserRepository mainUserRepository;

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
