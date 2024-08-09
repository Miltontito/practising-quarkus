package org.milton.book.acceso;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.milton.book.modelo.Author;

import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AccesoAuthor implements AccesoAuthorInterface{

    @Inject
    EntityManager em;

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Author> findAllAuthors() {
        //return em.createQuery("SELECT a FROM author a", Author.class).getResultList();
        return listAll();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Author findAuthorById(Long id) {
        if (id == null){
            return null;
        }
        return findById(id);
    }

    @Override
    public Author persistAuthor(Author author) {
        persist(author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        return em.merge(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        deleteById(id);
    }
}
