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
    public Category findCategoryById(Long id) {
        return findById(id);
    }

    @Override
    @Transactional
    public Category persistCategory(Category category) {
        persist(category);
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        return em.merge(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        deleteById(id);
    }
}
