

package org.milton.book.acceso;

/* Acceso a los datos de la base de datos, acá se encuentras cosas como actualizar o eliminar, persistir, etc. */
// Se utiliza el patrón Repository

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.milton.book.client.IsbnNumbers;
import org.milton.book.client.NumberProxy;
import org.milton.book.modelo.Book;
import org.milton.book.modelo.Category;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AccesoLibro implements AccesoLibroInterfaz{

    @Inject
    Logger LOGGER;

    @Inject
    EntityManager em;

    @Inject
    @RestClient
    NumberProxy numberProxy;

    @Override
    public Book persistBook(Book book) {

        //The book microservice invokes the number microservice
        IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
        book.setIsbn13(isbnNumbers.getIsbn13());
        book.setIsbn10(isbnNumbers.getIsbn10());

        persist(book);

        LOGGER.debug("Persisting Book...");
        return book;
    }

    //---------------------| Fallback Persist |---------------------
    @Override
    public Book fallbackPersistBook(Book book) throws FileNotFoundException {
        LOGGER.warn("Falling back on persisting a book");
        String bookJson = JsonbBuilder.create().toJson(book);
        try (PrintWriter out = new PrintWriter("book-"
                + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        }
        throw new IllegalStateException();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Book findRandomBook(){
        long countBooks = count();
        if (countBooks == 0){
            return null;
        }
        int randomBook = new Random().nextInt((int) countBooks);
        LOGGER.debug("Finding Random Book...");
        return findAll().page(randomBook, 1).firstResult();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findAllBooks() {
        LOGGER.debug("Listing All Books...");
        return em.createQuery("FROM Book", Book.class).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Book findBookById(Long id) {
        return findById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return em.merge(book);
    }

    @Override
    public void deleteBook(Long id) {
        deleteById(id);
    }


    // ----------------------------------| V0.1 |----------------------------------

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findAllBooksByAuthorId(Long id) {
        return find("SELECT b FROM Book b JOIN b.authors a WHERE a.id = ?1", id).list();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findBestBooks(Integer score) {
        return find("SELECT b FROM Book b WHERE b.rank > ?1", score).list();
    }


    //No devuelve todos los niveles, solo el primer nivel.
    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findBooksByCategory(String category){
        // FROM retorna todas las clases de la tabla

        return find("SELECT b FROM Book b WHERE b.category.id IN " +
                "(SELECT c.id FROM Category c WHERE c.name = ?1 " +
                "OR c.id IN (SELECT sc.id FROM Category sc WHERE sc.parentCategory.id = (SELECT c2.id FROM Category c2 WHERE c2.name = ?1)))", category).list();
    }
}
