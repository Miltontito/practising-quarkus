package org.milton.book.servicio;

import org.milton.book.acceso.AccesoAuthorInterface;
import org.milton.book.acceso.AccesoCategoryInteface;
import org.milton.book.acceso.AccesoLibroInterfaz;

import org.eclipse.microprofile.faulttolerance.Fallback;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.book.transferible.TransferibleAuthor;
import org.milton.book.transferible.TransferibleCategory;
import org.milton.book.transferible.TransferibleCreateUpdateLibro;
import org.milton.book.transferible.TransferibleLibro;
import org.milton.book.transformador.TransformadorAuthor;
import org.milton.book.transformador.TransformadorCategory;
import org.milton.book.transformador.TransformadorCreateUpdateLIbro;
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
    @Inject
    AccesoCategoryInteface accesoCategory;


    public TransferibleCreateUpdateLibro persistBook(TransferibleCreateUpdateLibro transferibleLibro) {

        // ----------------------------------| V0.1 |----------------------------------
        List<Long> author_ids = getAuthorIDs(transferibleLibro);
        Long category_id = getCategoryId(transferibleLibro.getCategory());
        return TransformadorCreateUpdateLIbro.INSTANCE.toLibroDTO(acceso.persistBook(TransformadorCreateUpdateLIbro.INSTANCE.toEntity(transferibleLibro), author_ids, category_id));
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
    public TransferibleCreateUpdateLibro updateBook(TransferibleCreateUpdateLibro transferibleLibro) {
        return TransformadorCreateUpdateLIbro.INSTANCE.toLibroDTO(acceso.updateBook(TransformadorCreateUpdateLIbro.INSTANCE.toEntity(transferibleLibro)));
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

    //Pasar metodo al área de Author?
    public List<Long> getAuthorIDs(TransferibleCreateUpdateLibro transferibleLibro){
        if(transferibleLibro.getAuthors() == null){
            return null;
        }
        List<Long> author_ids = new ArrayList<>();
        for (TransferibleAuthor autor : transferibleLibro.getAuthors()){
            if (autor.getId() != null){
                author_ids.add(autor.getId());
            }
            else{
                TransferibleAuthor autorPersistido = TransformadorAuthor.INSTANCE.toAuthorDTO(accesoAuthor.persistAuthor(TransformadorAuthor.INSTANCE.toEntity(autor)));
                author_ids.add(autorPersistido.getId());
            }
        }
        return author_ids;
    }


    //Pasar metodo al área de Category?

    //Devuelve el id de la tabla con todas las relaciones armadas.
    public Long getCategoryId(TransferibleCategory transferibleCategory){

        Long categoryId = null;
        if(transferibleCategory != null){
            if(transferibleCategory.getId() != null){
                categoryId = transferibleCategory.getId();
            }
            else{
                TransferibleCategory persistedCategory = TransformadorCategory.INSTANCE.toCategoryDTO(accesoCategory.persistCategory(TransformadorCategory.INSTANCE.toEntity(transferibleCategory)));
                categoryId = persistedCategory.getId();
            }
        }
        return categoryId;
    }

}
