package com.example.FinanceTracker.controllers;


import com.example.FinanceTracker.entities.Category;
import org.springframework.web.bind.annotation.*;
import com.example.FinanceTracker.services.implementation.CategoryService;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,@RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
    @GetMapping("/user/{userId}")
    public List<Category> getCategoriesByUser(
            @PathVariable Long userId){

        return categoryService.getCategoriesByUser(userId);

    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
