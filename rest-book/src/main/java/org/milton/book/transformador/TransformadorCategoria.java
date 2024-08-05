package org.milton.book.transformador;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Categoria;
import org.milton.book.transferible.TransferibleCategoria;

@Mapper
public interface TransformadorCategoria {
    TransformadorCategoria INSTANCE = Mappers.getMapper(TransformadorCategoria.class);

    // Entity to DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "parentCategoria", target = "parentCategoria")
    //@Mapping(source = "subCategoria", target = "subCategoria")
    TransferibleCategoria toCategoriaDTO(Categoria categoria);
}
