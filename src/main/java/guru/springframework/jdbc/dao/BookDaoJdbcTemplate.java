package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoJdbcTemplate implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", this.getBookMapper(), id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM book WHERE title = ?", this.getBookMapper(), title);
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update("UPDATE book set isbn = ?, publisher = ?, title = ?, author_id = ? where id = ?",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId(), book.getId());

        return this.getById(book.getId());
    }

    @Override
    public Book saveNewBook(Book book) {
        this.jdbcTemplate.update("INSERT INTO book (isbn, publisher, title, author_id) VALUES (?,?,?,?)",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId());

        Long createdId = this.jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(createdId);
    }

    @Override
    public void deleteBookById(Long id) {
        this.jdbcTemplate.update("DELETE from book WHERE id = ?", id);
    }

    private BookMapper getBookMapper() {
        return new BookMapper();
    }
}
