package bookshop.services;

import bookshop.entities.BookToOrder;
import bookshop.repos.BooksOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookOrderService {
    @Autowired
    private BooksOrderRepository booksOrderRepository;

    public List<Long> getBookIds(Long orderId){
    	return booksOrderRepository
    			.findAllByOrder_id(orderId).stream()
    			.map(BookToOrder::getBook_id)
    			.collect(Collectors.toList());
    }

    public void save(BookToOrder bookToOrder) {
        booksOrderRepository.save(bookToOrder);
    }
}
