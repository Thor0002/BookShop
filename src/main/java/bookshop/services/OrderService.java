package bookshop.services;

import bookshop.entities.Order;
import bookshop.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllByUserId(Long userId) {
        return orderRepository.findAllByUser_id(userId);
    }

    public Order getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElseGet(Order::new);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void update(Long id) {
        orderRepository.update(id);
    }

    public Long findTopByOrderByIdDesc() {
        return orderRepository.findTopByOrderByIdDesc();
    }
}
