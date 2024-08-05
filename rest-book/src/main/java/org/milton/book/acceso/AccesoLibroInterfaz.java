

package org.milton.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.milton.book.modelo.Autor;
import org.milton.book.modelo.Book;
import org.milton.book.modelo.Categoria;

import java.io.FileNotFoundException;
import java.util.List;

@ApplicationScoped
public interface AccesoLibroInterfaz extends PanacheRepository<Book> {
    Book findRandomBook();
    List<Book> findAllBooks();
    Book findBookById(Long id);
    Book persistBook(Book book);
    Book updateBook(Book book);
    void deleteBook(Long id);
    Book fallbackPersistBook(Book book) throws FileNotFoundException;

    // V0.1
    List<Book> findBestBooks(Double punctuation);
    List<Book> findBooksByCategory(Categoria categoria);
}
