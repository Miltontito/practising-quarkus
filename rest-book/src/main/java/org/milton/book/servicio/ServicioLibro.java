package org.milton.book.servicio;

import org.milton.book.acceso.AccesoLibroInterfaz;

import org.eclipse.microprofile.faulttolerance.Fallback;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.milton.book.modelo.Book;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ServicioLibro {

    @Inject
    AccesoLibroInterfaz acceso;

    //Persists a given book
    public Book persistBook(@Valid Book book) {
        return acceso.persistBook(book);
    }

    @Fallback(fallbackMethod = "fallbackPersistBook")
    public Book fallbackPersistBook(Book book) throws FileNotFoundException {
        return acceso.fallbackPersistBook(book);
    }

    //List of all books
    public List<Book> findAllBooks() {
        return acceso.findAllBooks();
    }

    //Finds the book by his id
    public Optional<Book> findBookById(Long id) {
        return acceso.findBookById(id);
    }

    //Finds a random book
    public Book findRandomBook() {
         return acceso.findRandomBook();
    }

    //Updates the given book
    public Book updateBook(@Valid Book book) {
        return acceso.updateBook(book);
    }

    //Deletes the given book through his id
    public void deleteBook(Long id) {
        acceso.deleteBook(id);
    }

}
