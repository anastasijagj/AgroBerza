package finki.ukim.mk.agroberza.service.impl;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.Order;
import finki.ukim.mk.agroberza.repository.OrderRepository;
import finki.ukim.mk.agroberza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByUser(MainUser user) {
        return this.orderRepository.findAllByUser(user);
    }

    @Override
    public Optional<Order> findByName(String name) {
        return this.orderRepository.findOrderByName(name);
    }
}
