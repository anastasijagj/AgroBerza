package finki.ukim.mk.agroberza.repository;

import finki.ukim.mk.agroberza.model.Naracka;
import finki.ukim.mk.agroberza.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Naracka, Long> {
    List<Naracka> findNarackasByOrderedByUserId(Long orderedByUserId);

    List<Naracka> findAllByOrderToUserId(Long orderToUserId);

    List<Naracka> findAllByProducts(Product product);

    Optional<Naracka> findById(Long id);
}
