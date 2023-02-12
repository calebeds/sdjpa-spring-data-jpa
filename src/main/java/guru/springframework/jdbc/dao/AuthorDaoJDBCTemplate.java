package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AuthorDaoJDBCTemplate implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }

    @Override
    public List<Author> findAllAuthorsByLastNameSortByFirstName(String lastName, Pageable pageable) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM author WHERE last_name = ? ");

        if(pageable.getSort().getOrderFor("firstname") != null) {
            sb.append("ORDER BY first_name ").append(pageable.getSort().getOrderFor("firstname").getDirection().name());
        }

        sb.append(" limit ? offset ?");

        return  jdbcTemplate.query(sb.toString(), getAuthorMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
        //        String sql = "SELECT * FROM author WHERE last_name = ? ORDER BY first_name " + pageable.getSort().getOrderFor("first_name").getDirection().name() + " limit ? offset ?";
//        return this.jdbcTemplate.query(sql, this.getAuthorMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
    }

    private AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }
}
