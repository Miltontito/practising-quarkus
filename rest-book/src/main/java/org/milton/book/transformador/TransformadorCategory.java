package org.milton.book.transformador;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.milton.book.modelo.Category;
import org.milton.book.transferible.TransferibleCategory;

import java.util.List;

@Mapper
public interface TransformadorCategory {

    TransformadorCategory INSTANCE = Mappers.getMapper(TransformadorCategory.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "parentCategory", source = "parentCategory")
    TransferibleCategory toCategoryDTO(Category category);

    @InheritInverseConfiguration
    Category toEntity(TransferibleCategory transferibleCategory);

    List<TransferibleCategory> toCategoryDTOList(List<Category> categoryList);

    List<Category> toEntityList(List<TransferibleCategory> transferibleCategoryList);
}
