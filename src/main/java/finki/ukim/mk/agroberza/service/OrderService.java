package finki.ukim.mk.agroberza.service;

import finki.ukim.mk.agroberza.model.Naracka;
import java.util.List;
import java.util.Set;

public interface OrderService {
    public List<Naracka> findAll();

    public List<Naracka> findAllByOrderedByUserId(Long userId);

    public List<Naracka> findAllByOrderToUserId(Long userId);

    public List<Naracka> findAllByProduct(Long id);

    public void deleteById(Long id);

    public Naracka addOrder(Naracka naracka);
}
