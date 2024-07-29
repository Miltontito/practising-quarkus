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
    public Book persistBook(@Valid Book book) {
        //The book microservice invokes the number microservice
        IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
        book.isbn13 = isbnNumbers.getIsbn13();
        book.isbn10 = isbnNumbers.getIsbn10();

        Book.persist(book);
        return book;
    }

    private Book fallbackPersistBook(Book book) throws FileNotFoundException {
        LOGGER.warn("Falling back on persisting a book");
        String bookJson = JsonbBuilder.create().toJson(book);
        try (PrintWriter out = new PrintWriter("book-"
                + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        }
        throw new IllegalStateException();
    }

    //List of all books
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findAllBooks() {
        return Book.listAll();
    }

    //Finds the book by his id
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<Book> findBookById(Long id) {
        return Book.findByIdOptional(id);
    }

    //Finds a random book
    @Transactional(Transactional.TxType.SUPPORTS)
    public Book findRandomBook() {
        Book randomBook = null;
        while (randomBook == null) {
            randomBook = Book.findRandom();
        }
        return randomBook;
    }

    //Updates the given book
    public Book updateBook(@Valid Book book) {
        Book entity = em.merge(book);
        return entity;
    }

    //Deletes the given book through his id
    public void deleteBook(Long id) {
        Book.deleteById(id);
    }

}
