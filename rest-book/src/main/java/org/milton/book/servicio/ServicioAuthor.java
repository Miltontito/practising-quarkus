package org.milton.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.book.acceso.AccesoAuthorInterface;
import org.milton.book.transferible.TransferibleAuthor;
import org.milton.book.transformador.TransformadorAuthor;

import java.util.List;

@ApplicationScoped
public class ServicioAuthor {

    @Inject
    AccesoAuthorInterface acceso;


    public TransferibleAuthor persistAuthor(TransferibleAuthor transferibleAuthor){
        return TransformadorAuthor.INSTANCE.toAuthorDTO(acceso.persistAuthor(TransformadorAuthor.INSTANCE.toEntity(transferibleAuthor)));
    }

    public TransferibleAuthor findAuthorById(Long id){
        return TransformadorAuthor.INSTANCE.toAuthorDTO(acceso.findAuthorById(id));
    }

    public List<TransferibleAuthor> findAllAuthors(){
        return TransformadorAuthor.INSTANCE.toLibroDTOList(acceso.findAllAuthors());
    }

    public TransferibleAuthor updateAuthor(TransferibleAuthor transferibleAuthor){
        return TransformadorAuthor.INSTANCE.toAuthorDTO(acceso.updateAuthor(TransformadorAuthor.INSTANCE.toEntity(transferibleAuthor)));
    }

    public void deleteAuthor(Long id){
        acceso.deleteAuthor(id);
    }

}
