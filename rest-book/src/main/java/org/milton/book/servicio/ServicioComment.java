package org.milton.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.book.acceso.AccesoCommentInterface;
import org.milton.book.transferible.TransferibleComment;
import org.milton.book.transformador.TransformadorComment;

import java.util.List;

@ApplicationScoped
public class ServicioComment {

    @Inject
    AccesoCommentInterface acceso;

    public TransferibleComment persistComment(TransferibleComment transferibleComment){
        return TransformadorComment.INSTANCE.toCommentDto(acceso.persistComment(TransformadorComment.INSTANCE.toEntity(transferibleComment)));
    }

    public TransferibleComment findCommentById(Long id){
        return TransformadorComment.INSTANCE.toCommentDto(acceso.findCommentById(id));
    }

    public List<TransferibleComment> findAllComments(){
        return TransformadorComment.INSTANCE.toCommentDTOList(acceso.findAllComments());
    }

    public TransferibleComment updateComment(TransferibleComment transferibleComment){
        return TransformadorComment.INSTANCE.toCommentDto(acceso.updateComment(TransformadorComment.INSTANCE.toEntity(transferibleComment)));
    }

    public void deleteComment(Long id){
       acceso.deleteComment(id);
    }
}
