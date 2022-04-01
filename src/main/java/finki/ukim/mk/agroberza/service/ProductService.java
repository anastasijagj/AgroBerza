package finki.ukim.mk.agroberza.service;

import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public Optional<Product> findProductByName(String name) {
        return this.productRepository.findProductByName(name);
    }
}
