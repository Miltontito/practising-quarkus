package org.milton.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.milton.book.modelo.Author;

import java.util.List;

@ApplicationScoped
public interface AccesoAuthorInterface extends PanacheRepository<Author> {
    List<Author> findAllAuthors();
    Author findAuthorById(Long id);
    Author persistAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);

}
