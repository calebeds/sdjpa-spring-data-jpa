package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;

public interface BookDao {
    Book getById(Long id);
    Book findBookByTitle(String title);
    Book updateBook(Book book);
    Book saveNewBook(Book book);
    void deleteBookById(Long id);
}
