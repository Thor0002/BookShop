package bookshop.repos;


import bookshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select cart from Cart cart where cart.user_id = ?1")
    List<Cart> getAllByUserId(Long userId);

    @Query("delete from Cart cart where cart.user_id=:userId")
    @Transactional
    @Modifying
    void deleteAllByUserId(@Param("userId") Long userId);
}
