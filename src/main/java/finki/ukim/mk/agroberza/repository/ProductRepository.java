package finki.ukim.mk.agroberza.repository;

import finki.ukim.mk.agroberza.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteById(Long id);

    List<Product> findProductByName(String name);

    List<Product> findAllByOwnerId(Long ownerId);
}
