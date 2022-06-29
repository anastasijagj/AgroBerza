package finki.ukim.mk.agroberza.service.impl;

import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.repository.ProductRepository;
import finki.ukim.mk.agroberza.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findAllByOwnerId(Long id) {
        return this.productRepository.findAllByOwnerId(id);
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public List<Product> findProductByName(String name) {
        return this.productRepository.findProductByName(name);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Product edit(Long id, String name, Double price, Integer quantity) {
        Product product = this.productRepository.findById(id).get();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return this.productRepository.save(product);
    }

    @Override
    public Product add(String name, Double price, Integer quantity, Long ownerId) {
        Product product = new Product(name, price, quantity, ownerId);
        return this.productRepository.save(product);
    }
}
