package bookshop.repos;

import bookshop.entities.Book;
import bookshop.entities.BookToOrder;
import bookshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksOrderRepository extends JpaRepository<BookToOrder, Long> {
//    @Query("select bO from BookToOrder bO where bO.order_id = ?1")
//    List<Book> findAllByOrderId(Long orderId);

    @Query("select bO from BookToOrder bO where bO.order_id = ?1")
    List<BookToOrder> findAllByOrder_id(Long orderId);
}
