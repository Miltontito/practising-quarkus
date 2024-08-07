package org.milton.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.milton.book.modelo.Category;

import java.util.List;

@ApplicationScoped
public interface AccesoCategoryInteface extends PanacheRepository<Category> {
    List<Category> findAllCategories();
    Category findCategoryById(Long id);
    Category persistCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
}
