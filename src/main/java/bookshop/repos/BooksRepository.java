package bookshop.repos;

import bookshop.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {

    @Query("select book from Book book where book.book_id = ?1")
    Book findByBook_id(Long bookId);

    @Query("select book from Book book where book.title = ?1")
    List<Book> findAllByTitle(String title);

    @Query("select book from Book book where book.author = ?1")
    List<Book> findAllByAuthor(String author);
}
