package org.milton.book.servicio;

import org.milton.book.acceso.AccesoAuthorInterface;
import org.milton.book.acceso.AccesoLibroInterfaz;

import org.eclipse.microprofile.faulttolerance.Fallback;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.book.transferible.TransferibleAuthor;
import org.milton.book.transferible.TransferibleLibro;
import org.milton.book.transformador.TransformadorAuthor;
import org.milton.book.transformador.TransformadorLibro;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioLibro {

    @Inject
    AccesoLibroInterfaz acceso;
    @Inject
    AccesoAuthorInterface accesoAuthor;

    public TransferibleLibro persistBook(TransferibleLibro transferibleLibro) {

        // buscamos los Id de los autores enviados por transferibleLibro
        List<Long> author_ids = new ArrayList<>();
        for (TransferibleAuthor autor : transferibleLibro.getAuthors()){
            if (autor.getId() != null){
                // Si el id enviado no está vacío, lo añade.
                author_ids.add(autor.getId());
            }
            else{
                // Si el id está vacio lo persiste.
                TransferibleAuthor autorPersistido = TransformadorAuthor.INSTANCE.toAuthorDTO(accesoAuthor.persistAuthor(TransformadorAuthor.INSTANCE.toEntity(autor)));
                author_ids.add(autorPersistido.getId());
            }
        }

        // devolvemos la lista de IDs de los autores
        return TransformadorLibro.INSTANCE.toLibroDTO(acceso.persistBook(TransformadorLibro.INSTANCE.toEntity(transferibleLibro), author_ids));
    }

    @Fallback(fallbackMethod = "fallbackPersistBook")
    public TransferibleLibro fallbackPersistBook(TransferibleLibro transferibleLibro) throws FileNotFoundException {
        return TransformadorLibro.INSTANCE.toLibroDTO(acceso.fallbackPersistBook(TransformadorLibro.INSTANCE.toEntity(transferibleLibro)));
    }

    //List of all books
    public List<TransferibleLibro> findAllBooks() {
        return TransformadorLibro.INSTANCE.toLibroDTOList(acceso.findAllBooks());
    }

    //Finds the book by his id
    public TransferibleLibro findBookById(Long id) {
        return TransformadorLibro.INSTANCE.toLibroDTO(acceso.findBookById(id));
    }

    //Finds a random book
    public TransferibleLibro findRandomBook() {
         return TransformadorLibro.INSTANCE.toLibroDTO(acceso.findRandomBook());
    }

    //Updates the given book
    public TransferibleLibro updateBook(TransferibleLibro transferibleLibro) {
        return TransformadorLibro.INSTANCE.toLibroDTO(acceso.updateBook(TransformadorLibro.INSTANCE.toEntity(transferibleLibro)));
    }

    //Deletes the given book through his id
    public void deleteBook(Long id) {
        acceso.deleteBook(id);
    }

    // ----------------------------------| V0.1 |----------------------------------
    public List<TransferibleLibro> findAllBooksByAuthorId(Long id){
        return TransformadorLibro.INSTANCE.toLibroDTOList(acceso.findAllBooksByAuthorId(id));
    }

    public List<TransferibleLibro> findBestBooks(Integer score){
        return TransformadorLibro.INSTANCE.toLibroDTOList(acceso.findBestBooks(score));
    }

    public List<TransferibleLibro> findBooksByCategory(String category){
        return TransformadorLibro.INSTANCE.toLibroDTOList(acceso.findBooksByCategory(category));
    }

}
