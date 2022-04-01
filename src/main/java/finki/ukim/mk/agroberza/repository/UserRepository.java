package finki.ukim.mk.agroberza.repository;

import finki.ukim.mk.agroberza.model.MainUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MainUser, Long> {
}
