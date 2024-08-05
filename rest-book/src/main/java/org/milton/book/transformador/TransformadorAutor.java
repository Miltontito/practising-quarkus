package org.milton.book.transformador;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Autor;
import org.milton.book.transferible.TransferibleAutor;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface TransformadorAutor {
    TransformadorAutor INSTANCE = Mappers.getMapper(TransformadorAutor.class);

    //Entidad a DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "nacionalidad", target = "nacionalidad")
    @Mapping(source = "book", target = "book")
    TransferibleAutor toAutorDTO(Autor autor);

    //DTO a Entidad
    @InheritInverseConfiguration
    Autor toEntity(TransferibleAutor transferibleAutor);

    //Listas de DTOs a Listas de Entidades
    List<TransferibleAutor> toAutorDTOList(List<Autor> autorList);

    //Listas de Entidades a Listas de DTOs
    List<Autor> toEntityList(List<TransferibleAutor> transferibleAutorList);

}
