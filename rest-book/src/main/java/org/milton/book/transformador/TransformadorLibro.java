package org.milton.book.transformador;

import jakarta.ws.rs.OPTIONS;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Book;
import org.milton.book.transferible.TransferibleLibro;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface TransformadorLibro {
    TransformadorLibro INSTANCE = Mappers.getMapper(TransformadorLibro.class);

    //Entidad a DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "yearOfPublication", target = "yearOfPublication")
    @Mapping(source = "nbOfPages", target = "nbOfPages")
    @Mapping(source = "rank", target = "rank")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "smallImageUrl", target = "smallImageUrl")
    @Mapping(source = "mediumImageUrl", target = "mediumImageUrl")
    @Mapping(source = "description", target = "description")
    TransferibleLibro ToLibroDTO(Book book);

    //DTO a entidad
    @InheritInverseConfiguration
    Book toEntity(TransferibleLibro transferibleLibro);

    //Listas de DTOs a listas de Entidades
    List<TransferibleLibro> toLibroDTOList(List<Book> bookList);

    //Listas de Entidades a listas de DTOs
    List<Book> toEntityList(List<TransferibleLibro> transferibleLibroList);
}
