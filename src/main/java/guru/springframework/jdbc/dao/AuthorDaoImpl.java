package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 8/28/21.
 */
@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return this.authorRepository.findById(id).orElseThrow();
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return this.authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author foundAuthor = this.getById(author.getId());
        foundAuthor.setFirstName(author.getFirstName());
        foundAuthor.setLastName(author.getLastName());

        return this.authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
