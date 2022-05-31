package bookshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "books_orders")
public class BookToOrder {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bOrder_id;

    private Long order_id;
    private Long book_id;
    
    public Long getBOrder_id() {
        return bOrder_id;
    }

    public void setBOrder_id(Long id) {
        this.bOrder_id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
}
