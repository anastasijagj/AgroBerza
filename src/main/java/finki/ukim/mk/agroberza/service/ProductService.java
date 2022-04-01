package finki.ukim.mk.agroberza.service;

import finki.ukim.mk.agroberza.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findProductByName(String name);
}
