package bookshop.services;

import bookshop.entities.Book;
import bookshop.entities.Cart;
import bookshop.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    BookService bookService;

    public List<Long> getBookIds(Long userId) {
        return cartRepository
                .getAllByUserId(userId)
                .stream()
                .map(Cart::getBooks_id)
                .collect(Collectors.toList());
    }

    public List<Book> getBooks(Long userId) {
        return getBookIds(userId)
                .stream()
                .map(bookService::getById)
                .collect(Collectors.toList());
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteAllByUserId(Long userId) {
        cartRepository.deleteAllByUserId(userId);
    }
}
