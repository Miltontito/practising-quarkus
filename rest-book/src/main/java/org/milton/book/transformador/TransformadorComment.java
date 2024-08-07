package org.milton.book.transformador;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Comment;
import org.milton.book.transferible.TransferibleComment;

import java.util.List;

@Mapper
public interface TransformadorComment {

    TransformadorComment INSTANCE = Mappers.getMapper(TransformadorComment.class);

    TransferibleComment toCommentDto(Comment comment);

    @InheritInverseConfiguration
    Comment toEntity(TransferibleComment transferibleComment);

    List<TransferibleComment> toCommentDTOList(List<Comment> commentList);

    List<Comment> toEntityList(List<TransferibleComment> transferibleCommentList);

}
