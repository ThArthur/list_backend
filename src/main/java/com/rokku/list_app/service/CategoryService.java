package com.rokku.list_app.service;

import com.rokku.list_app.dto.category.CategoryResponse;
import com.rokku.list_app.models.Category;
import com.rokku.list_app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Long id, Category category) {
        Category categoryToUpdate = getCategoryById(id);

        if (category.getName() != null && !category.getName().isEmpty()) {
            categoryToUpdate.setName(category.getName());
        }

        return categoryRepository.save(categoryToUpdate);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

}
