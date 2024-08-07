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

    public TransferibleCategory findCategoryById(){
        return null;
    }

    public TransferibleCategory persistCategoryById(){
        return null;
    }

    public TransferibleCategory updateCategory(){
        return null;
    }

    public void deleteCategory(){
    }

}
