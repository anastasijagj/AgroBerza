package finki.ukim.mk.agroberza.service;

import finki.ukim.mk.agroberza.model.Naracka;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.swing.text.html.Option;

public interface OrderService {
    public List<Naracka> findAll();

    public List<Naracka> findAllByOrderedByUserId(Long userId);

    public List<Naracka> findAllByOrderToUserId(Long userId);

    public List<Naracka> findAllByProduct(Long id);

    public void deleteById(Long id);

    public Optional<Naracka> findById(Long id);
    public void removeProductFromOrder(Long productId, Long orderId);
    public Naracka addOrder(Naracka naracka);
}
