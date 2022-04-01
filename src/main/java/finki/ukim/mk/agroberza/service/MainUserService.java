package finki.ukim.mk.agroberza.service;

import finki.ukim.mk.agroberza.model.MainUser;
import java.util.List;
import java.util.Optional;

public interface MainUserService {
    List<MainUser> findAll();
    Optional<MainUser> findById(Long id);
    Optional<MainUser> findUserByName(String name);
}
