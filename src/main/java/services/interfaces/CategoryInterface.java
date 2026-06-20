package services.interfaces;

import entities.Category;
import repositories.CategoryRepository;

import java.util.List;

public interface CategoryInterface {
    Category createCategory(Category category);

    Category updateCategory(Long id, Category category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    void deleteCategory(Long id);
}
