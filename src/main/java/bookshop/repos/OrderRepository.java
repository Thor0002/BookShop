package bookshop.repos;


import bookshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select order from Order order where order.user_id = ?1")
    List<Order> findAllByUser_id(Long user_id);

    @Query(value = "select order_id from orders order by order_id desc limit 1", nativeQuery = true)
    Long findTopByOrderByIdDesc();


    @Transactional
    @Modifying
    @Query("update Order o set o.payed=true, o.status='payed' where o.id=?1")
    void update(Long id);
}