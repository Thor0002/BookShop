package bookshop.services;

import bookshop.entities.Book;
import bookshop.repos.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Configurable
public class BookService {
    @Autowired
    BooksRepository booksRepository;

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book getById(Long id) {
        return booksRepository.findByBook_id(id);
    }

    public List<Book> findAllByContainsTitle(String title) {
        return booksRepository
                .findAll()
                .stream()
                .filter(b -> b.getTitle().trim().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public List<Book> findAllByAuthor(String authorName) {
        return booksRepository
                .findAll()
                .stream()
                .filter(b -> b.getAuthor().toLowerCase(Locale.ROOT).contains(authorName.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
