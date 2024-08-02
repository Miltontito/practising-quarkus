package org.milton.book.servicio;

import org.milton.book.acceso.AccesoLibroInterfaz;

import org.eclipse.microprofile.faulttolerance.Fallback;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.milton.book.transferible.TransferibleLibro;
import org.milton.book.transformador.TransformadorLibro;

import java.io.FileNotFoundException;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ServicioLibro {

    @Inject
    AccesoLibroInterfaz acceso;

    //Persists a given book
    public TransferibleLibro persistBook(TransferibleLibro transferibleLibro) {
        //------------------------| toLibroDTO(Libro) -> DTO |------------------------
        return TransformadorLibro.INSTANCE.ToLibroDTO(acceso.persistBook(TransformadorLibro.INSTANCE.toEntity(transferibleLibro)));
    }

    @Fallback(fallbackMethod = "fallbackPersistBook")
    public TransferibleLibro fallbackPersistBook(TransferibleLibro transferibleLibro) throws FileNotFoundException {
        return TransformadorLibro.INSTANCE.ToLibroDTO(acceso.fallbackPersistBook(TransformadorLibro.INSTANCE.toEntity(transferibleLibro)));
    }

    //List of all books
    public List<TransferibleLibro> findAllBooks() {
        return TransformadorLibro.INSTANCE.toLibroDTOList(acceso.findAllBooks());
    }

    //Finds the book by his id
    public TransferibleLibro findBookById(Long id) {
        return TransformadorLibro.INSTANCE.ToLibroDTO(acceso.findBookById(id));
    }

    //Finds a random book
    public TransferibleLibro findRandomBook() {
         return TransformadorLibro.INSTANCE.ToLibroDTO(acceso.findRandomBook());
    }

    //Updates the given book
    public TransferibleLibro updateBook(TransferibleLibro transferibleLibro) {
        return TransformadorLibro.INSTANCE.ToLibroDTO(acceso.updateBook(TransformadorLibro.INSTANCE.toEntity(transferibleLibro)));
    }

    //Deletes the given book through his id
    public void deleteBook(Long id) {
        acceso.deleteBook(id);
    }

}
