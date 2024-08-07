package org.milton.book.acceso;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.milton.book.modelo.Category;

import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AccesoCategory implements AccesoCategoryInteface{

    @Inject
    EntityManager em;

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Category> findAllCategories() {
        return listAll();
    }

    @Override
    public Category findAuthorById(Long id) {
        return null;
    }

    @Override
    public Category persistCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
