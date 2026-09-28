package com.app.meme_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import com.app.meme_service.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.meme_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController()
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try{
            log.info("POST /category - createCategory categoryDTO={}", categoryDTO);
            return ResponseEntity.ok().body(categoryService.createCategory(categoryDTO));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryDTO categoryDTO) {
        try{
            log.info("PUT /category/update-category/{id} - updateCategory categoryDTO={}", categoryDTO);
            return ResponseEntity.ok().body(categoryService.updateCategory(id, categoryDTO));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/delete-category/{id}")
    public void deleteCategory(@PathVariable(name = "id") Long id) {
        try{
            log.info("DELETE /category/delete-category/{id} - deleteCategory categoryId={}", id);
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/list-category")
    public void listCategory() {
        try{
            log.info("GET /list-category - listCategory");
            categoryService.listCategory();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
