package com.app.meme_service.service;

import com.app.meme_service.dto.CategoryDTO;
import com.app.meme_service.entity.CategoryEntity;
import com.app.meme_service.exception.category_exception.CategoryNotFoundException;
import com.app.meme_service.exception.category_exception.CategoryAlreadyExistsException;
import com.app.meme_service.repository.CategoryRepository;
import com.app.meme_service.utils.CategoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        log.info("creating a meme");

        if(categoryRepository.existsByName(categoryDTO.name())) {
            log.warn("existing category category={}" + categoryDTO.name());
            throw  new CategoryAlreadyExistsException("existing category");
        }

        CategoryEntity category = CategoryUtils.convert(categoryDTO);

        log.info("Category successfully created");
        categoryRepository.save(category);

        return categoryDTO;
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO){
        log.info("updating the category id={}" + id);

        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("category not found id={}" + id);
                    throw new CategoryNotFoundException("category not found");
        });

        CategoryUtils.updateCategory(entity, categoryDTO);

        log.info("Category successfully updated.");
        categoryRepository.save(entity);

        return categoryDTO;
    }

    public void deleteCategory(Long id){
        log.info("delete category");

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("category not found id={}" + id);
                    throw  new CategoryNotFoundException("category not found");
                });

        log.info("Category deleted successfully.");
        categoryRepository.deleteById(category.getId());
    }

    public List<CategoryEntity> listCategory(){
        log.info("List categories");

        List<CategoryEntity> categories = categoryRepository.findAll();

        log.info("Number of categories found []" + categories.size());
        return categories;
    }
}
