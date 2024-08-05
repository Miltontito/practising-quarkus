package org.milton.book.transformador;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Comentario;
import org.milton.book.transferible.TransferibleComentario;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface TransformadorComentario {
    TransformadorComentario INSTANCE = Mappers.getMapper(TransformadorComentario.class);

    // Entity to DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "email_creador", target = "email_creador")
    @Mapping(source = "texto", target = "texto")
    @Mapping(source = "puntuacion", target = "puntuacion")
    TransferibleComentario toComentarioDTO(Comentario comentario);

    // DTO to Entity
    @InheritInverseConfiguration
    Comentario toEntity (TransferibleComentario transferibleComentario);

    // Entity List to DTO List
    List<TransferibleComentario> toComentarioDTOList (List<Comentario> comentarioList);

    // DTO List to Entity List
    List<Comentario> toEntityList(List<TransferibleComentario> transferibleComentarioList);
}
