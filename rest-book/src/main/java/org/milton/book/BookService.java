package org.milton.book;

import org.milton.book.client.IsbnNumbers;
import org.milton.book.client.NumberProxy;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.milton.book.modelo.Libro;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookService {

    private static final Logger LOGGER = Logger.getLogger(BookService.class);


    @Inject
    EntityManager em;


    @Inject
    @RestClient
    NumberProxy numberProxy;

    //Rest-Book al ser cliente de Rest-Number se ve comprometido si este ultimo cae, por lo que en caso de que eso ocurra trata de salvarlo.
    @Fallback(fallbackMethod = "fallbackPersistBook")

    //Persists a given book
    public Libro persistBook(@Valid Libro libro) {
        //The book microservice invokes the number microservice
        IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
        libro.isbn13 = isbnNumbers.getIsbn13();
        libro.isbn10 = isbnNumbers.getIsbn10();

        Libro.persist(libro);
        return libro;
    }

    private Libro fallbackPersistBook(Libro libro) throws FileNotFoundException {
        LOGGER.warn("Falling back on persisting a book");
        String bookJson = JsonbBuilder.create().toJson(libro);
        try (PrintWriter out = new PrintWriter("book-"
                + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        }
        throw new IllegalStateException();
    }

    //List of all books
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Libro> findAllBooks() {
        return Libro.listAll();
    }

    //Finds the book by his id
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<Libro> findBookById(Long id) {
        return Libro.findByIdOptional(id);
    }

    //Finds a random book
    @Transactional(Transactional.TxType.SUPPORTS)
    public Libro findRandomBook() {
        Libro randomLibro = null;
        while (randomLibro == null) {
            randomLibro = Libro.findRandom();
        }
        return randomLibro;
    }

    //Updates the given book
    public Libro updateBook(@Valid Libro libro) {
        Libro entity = em.merge(libro);
        return entity;
    }

    //Deletes the given book through his id
    public void deleteBook(Long id) {
        Libro.deleteById(id);
    }

}
