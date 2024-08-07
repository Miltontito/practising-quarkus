package org.milton.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.milton.book.acceso.AccesoCategoryInteface;

import org.milton.book.transferible.TransferibleCategory;
import org.milton.book.transformador.TransformadorCategory;

import java.util.List;

@ApplicationScoped
public class ServicioCategory {

    @Inject
    AccesoCategoryInteface acceso;

    public List<TransferibleCategory> findAllCategories(){
        return TransformadorCategory.INSTANCE.toCategoryDTOList(acceso.findAllCategories());
    }

    public TransferibleCategory findCategoryById(Long id){
        return TransformadorCategory.INSTANCE.toCategoryDTO(acceso.findCategoryById(id));
    }

    public TransferibleCategory persistCategory(TransferibleCategory transferibleCategory){
        return TransformadorCategory.INSTANCE.toCategoryDTO(acceso.persistCategory(TransformadorCategory.INSTANCE.toEntity(transferibleCategory)));
    }

    public TransferibleCategory updateCategory(TransferibleCategory transferibleCategory){
        return TransformadorCategory.INSTANCE.toCategoryDTO(acceso.updateCategory(TransformadorCategory.INSTANCE.toEntity(transferibleCategory)));
    }

    public void deleteCategory(Long id){
        acceso.deleteCategoryById(id);
    }

}
