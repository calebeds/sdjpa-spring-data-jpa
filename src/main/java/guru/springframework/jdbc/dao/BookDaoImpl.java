package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable);


        return bookPage.getContent();
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        Pageable pageable = PageRequest.ofSize(pageSize);

        pageable = offset > 0 ? pageable.withPage(offset / pageSize) : pageable.withPage(0);

        return this.findAllBooks(pageable);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return this.bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book findBookByTitle(String title) {
        return this.bookRepository.findBookByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        Book foundBook = this.getById(book.getId());
        foundBook.setTitle(book.getTitle());
        foundBook.setIsbn(book.getIsbn());
        foundBook.setPublisher(book.getPublisher());
        foundBook.setAuthorId(book.getAuthorId());

        return this.bookRepository.save(foundBook);
    }

    @Override
    public Book saveNewBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
