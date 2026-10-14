package br.com.ebac.memelandia.service;

import br.com.ebac.memelandia.dto.CategoryDTO;
import br.com.ebac.memelandia.feign.CategoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryFeign categoryFeign;

    public CategoryDTO createCategory(CategoryDTO category) {
        CategoryDTO categoryDTO = new CategoryDTO(category.getName(), category.getDescription());

        categoryFeign.createCategory(category);

        return categoryDTO;
    }

    public CategoryDTO updateCategory(CategoryDTO category, Long categoryID) {
        CategoryDTO categoryDTO = new CategoryDTO(category.getName(), category.getDescription());

        categoryFeign.updateCategory(category, categoryID);

        return categoryDTO;
    }

    public void deleteCategory(Long categoryID) {
        categoryFeign.deleteCategory(categoryID);
    }

    public List<CategoryDTO> listCategory() {
        return categoryFeign.listCategory();
    }
}
