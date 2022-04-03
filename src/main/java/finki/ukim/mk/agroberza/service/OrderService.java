package finki.ukim.mk.agroberza.service;

import finki.ukim.mk.agroberza.model.MainUser;
import finki.ukim.mk.agroberza.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> findAll();
    public List<Order> findAllByUser(MainUser user);
    public Optional<Order> findByName(String name);
}
