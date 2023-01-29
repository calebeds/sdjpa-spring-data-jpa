package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {
    List<Book> findAllBooks(Pageable pageable);
    List<Book> findAllBooks(int pageSize, int offset);
    List<Book> findAllBooks();
    Book getById(Long id);
    Book findBookByTitle(String title);
    Book updateBook(Book book);
    Book saveNewBook(Book book);
    void deleteBookById(Long id);
}
