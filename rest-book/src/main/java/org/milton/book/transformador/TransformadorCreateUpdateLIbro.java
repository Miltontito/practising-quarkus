package org.milton.book.transformador;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Book;
import org.milton.book.transferible.TransferibleCreateUpdateLibro;

import java.util.List;

@Mapper
public interface TransformadorCreateUpdateLIbro {
    TransformadorCreateUpdateLIbro INSTANCE = Mappers.getMapper(TransformadorCreateUpdateLIbro.class);

    //Entidad a DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "isbn13", target = "isbn13")
    @Mapping(source = "isbn10", target = "isbn10")
    @Mapping(source = "yearOfPublication", target = "yearOfPublication")
    @Mapping(source = "nbOfPages", target = "nbOfPages")
    @Mapping(source = "rank", target = "rank")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "smallImageUrl", target = "smallImageUrl")
    @Mapping(source = "mediumImageUrl", target = "mediumImageUrl")
    @Mapping(source = "description", target = "description")

    @Mapping(source = "authors", target = "authors")
    @Mapping(source = "category", target = "category")
    TransferibleCreateUpdateLibro toLibroDTO(Book book);

    //DTO a entidad
    @InheritInverseConfiguration
    Book toEntity(TransferibleCreateUpdateLibro transferibleCreateUpdateLibro);

    //Listas de DTOs a listas de Entidades
    List<TransferibleCreateUpdateLibro> toLibroDTOList(List<Book> bookList);

    //Listas de Entidades a listas de DTOs
    List<Book> toEntityList(List<TransferibleCreateUpdateLibro> transferibleCreateUpdateLibroList);
}