package com.example.FinanceTracker.services.implementation;

import com.example.FinanceTracker.entities.Category;
import com.example.FinanceTracker.entities.User;
import com.example.FinanceTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.FinanceTracker.repositories.CategoryRepository;
import com.example.FinanceTracker.services.interfaces.CategoryInterface;

import java.util.List;
@Service
public class CategoryService implements CategoryInterface {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,  UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Category createCategory(Category category) {

        User user = userRepository.findById(
                category.getUser().getUserId()
        ).orElseThrow(() -> new RuntimeException("User not found"));


        category.setUser(user);


        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = getCategoryById(id);

        category.setCategoryName(updatedCategory.getCategoryName());
        category.setCategoryType(updatedCategory.getCategoryType());

        return categoryRepository.save(category);
    }
    public List<Category> getCategoriesByUser(Long userId){

        return categoryRepository.findByUserUserId(userId);

    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
