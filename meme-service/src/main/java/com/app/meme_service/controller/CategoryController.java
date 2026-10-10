package com.app.meme_service.controller;

import com.app.meme_service.dto.CategoryDTO;
import com.app.meme_service.entity.CategoryEntity;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.meme_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    private ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<CategoryDTO>  updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    private ResponseEntity<List<CategoryEntity>> listCategory() {
         List<CategoryEntity> categories = categoryService.listCategory();

         return ResponseEntity.ok(categories);
    }
}
