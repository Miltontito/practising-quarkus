package org.milton.book.acceso;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jboss.logging.Logger;
import org.milton.book.modelo.Autor;
import org.milton.book.modelo.Book;

import java.util.List;
public class AccesoAutor implements AccesoAutorInterface{

    @Inject
    Logger LOGGER;
    @Inject
    EntityManager em;

    @Inject
    AccesoLibroInterfaz accesoLibro;

    @Override
    public List<Autor> findAllAuthors() {
        LOGGER.debug("Listing all Authors...");
        return listAll();
    }
    @Override
    public Autor findAuthorById(Long id) {
        return findById(id);
    }
    @Override
    public Autor persistAuthor(Autor autor) {
        em.persist(autor);
        LOGGER.debug("Persisting author...");
        return autor;
    }
    @Override
    public Autor updateAuthor(Autor autor) {
        return em.merge(autor);
    }
    @Override
    public void deleteAuthor(Long id) {
        deleteById(id);
    }

    @Override
    public List<Book> findAllAuthorBooks(Autor autor) {
        return List.of();
    }


    // <-------------------------| V0.1 |------------------------->

    /*
    @Override
    public List<Book> findAllAuthorBooks(Autor autor) {
        for (Book b : accesoLibro.findAllBooks()){
            autor.
        }
        return List.of();
    }


     */
}
