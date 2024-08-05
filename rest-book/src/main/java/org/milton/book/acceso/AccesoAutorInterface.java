package org.milton.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.milton.book.modelo.Autor;
import org.milton.book.modelo.Book;

import java.util.List;

@ApplicationScoped
public interface AccesoAutorInterface extends PanacheRepository<Autor> {

    List<Autor> findAllAuthors();
    Autor findAuthorById(Long id);
    Autor persistAuthor(Autor autor);
    Autor updateAuthor(Autor autor);
    void deleteAuthor(Long id);

    // V0.1
    List<Book> findAllAuthorBooks(Autor autor);

}
