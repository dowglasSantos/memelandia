package br.com.ebac.memelandia.controllers;

import br.com.ebac.memelandia.dto.CategoryDTO;
import br.com.ebac.memelandia.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping()
    public CategoryDTO createCategory(@RequestBody CategoryDTO category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{categoryID}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO category, @PathVariable(name = "categoryID") Long categoryID) {
        return categoryService.updateCategory(category, categoryID);
    }

    @DeleteMapping("/{categoryID}")
    public void deleteCategory(@PathVariable(name = "categoryID") Long categoryID) {
        categoryService.deleteCategory(categoryID);
    }

    @GetMapping()
    public List<CategoryDTO> listCategory() {
        return categoryService.listCategory();
    }
}
