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
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("/{categoryID}")
    public ResponseEntity<CategoryDTO>  updateCategory(@PathVariable(name = "categoryID") Long categoryID, @RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryID, category));
    }

    @DeleteMapping("/{categoryID}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "categoryID") Long categoryID) {
        categoryService.deleteCategory(categoryID);

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<CategoryEntity>> listCategory() {
         List<CategoryEntity> categories = categoryService.listCategory();

         return ResponseEntity.ok(categories);
    }
}
