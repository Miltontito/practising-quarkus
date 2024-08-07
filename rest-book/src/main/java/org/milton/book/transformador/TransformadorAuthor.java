package org.milton.book.transformador;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Author;
import org.milton.book.transferible.TransferibleAuthor;

import java.util.List;

@Mapper
public interface TransformadorAuthor {
    TransformadorAuthor INSTANCE = Mappers.getMapper(TransformadorAuthor.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surName", target = "surName")
    @Mapping(source = "nationality", target = "nationality")
    TransferibleAuthor toAuthorDTO(Author author);

    @InheritInverseConfiguration
    Author toEntity(TransferibleAuthor transferibleAuthor);

    List<TransferibleAuthor> toLibroDTOList(List<Author> authorList);

    List<Author> toEntityList(List<TransferibleAuthor> transferibleAuthorList);

}
