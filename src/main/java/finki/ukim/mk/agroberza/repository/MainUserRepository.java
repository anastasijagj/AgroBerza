package finki.ukim.mk.agroberza.repository;

import finki.ukim.mk.agroberza.model.MainUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainUserRepository extends JpaRepository<MainUser, Long> {
    Optional<MainUser> findMainUserByName(String name);
    Optional<MainUser> findMainUserByUsername(String username);
}
