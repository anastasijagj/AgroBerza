package finki.ukim.mk.agroberza.service.impl;

import finki.ukim.mk.agroberza.model.Naracka;
import finki.ukim.mk.agroberza.model.Product;
import finki.ukim.mk.agroberza.repository.OrderRepository;
import finki.ukim.mk.agroberza.service.OrderService;
import finki.ukim.mk.agroberza.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;

    @Override
    public List<Naracka> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Naracka> findAllByOrderedByUserId(Long userId) {
        return this.orderRepository.findNarackasByOrderedByUserId(userId);
    }

    @Override
    public List<Naracka> findAllByOrderToUserId(Long userId) {
        return this.orderRepository.findAllByOrderToUserId(userId);
    }

    @Override
    public List<Naracka> findAllByProduct(Long id) {
        Product product = this.productService.findById(id).orElseThrow(() -> new RuntimeException());
        return this.orderRepository.findAllByProducts(product);
    }

    @Override
    public void deleteById(Long id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public Optional<Naracka> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public void removeProductFromOrder(Long productId, Long orderId) {
        Naracka order = this.orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException());
        List<Product> products = order.getProducts();
        List<Product> newProducts = new ArrayList<>();
        for (Product p : products) {
            if (!(p.getId().equals(productId))) {
                newProducts.add(p);
            }
        }
        order.removeAllProductsFromOrder();
        order.addProductsToOrder(newProducts);
        this.orderRepository.deleteById(orderId);
        this.orderRepository.save(order);
    }

    @Override
    public Naracka addOrder(Naracka naracka) {
        return this.orderRepository.save(naracka);
    }
}
