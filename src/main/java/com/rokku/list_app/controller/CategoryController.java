package com.rokku.list_app.controller;

import com.rokku.list_app.dto.category.CategoryResponse;
import com.rokku.list_app.dto.category.CreateCategoryRequest;
import com.rokku.list_app.models.Category;
import com.rokku.list_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories().stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()).
                collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);

        if (category == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build());
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category categoryUpdated = categoryService.updateCategory(id, category);

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(categoryUpdated.getId())
                .name(categoryUpdated.getName())
                .build();

        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest categoryRequest) {
        Category savedCategory = categoryService.createCategory(categoryRequest.toCategory());
        return ResponseEntity.ok(savedCategory);
    }
}
