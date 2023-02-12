package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AuthorDaoHibernate implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
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
       EntityManager em = this.getEntityManager();

       try {
           String hql = "SELECT a FROM Author a WHERE a.lastName = :lastName ";

           if(pageable.getSort().getOrderFor("firstname") != null) {
               hql = hql + "ORDER BY a.firstName " + pageable.getSort().getOrderFor("firstname").getDirection().name();
           }

           TypedQuery<Author> query = em.createQuery(hql, Author.class);
           query.setParameter("lastName", lastName);
           query.setFirstResult(Math.toIntExact(pageable.getOffset()));
           query.setMaxResults(pageable.getPageSize());

           return query.getResultList();
       } finally {
           em.close();
       }
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }
}
