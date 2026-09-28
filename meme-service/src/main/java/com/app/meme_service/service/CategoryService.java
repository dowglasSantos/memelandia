package com.app.meme_service.service;

import com.app.meme_service.dto.CategoryDTO;
import com.app.meme_service.entity.CategoryEntity;
import com.app.meme_service.repository.CategoryRepository;
import com.app.meme_service.utils.CategoryUtils;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
       if(categoryRepository.existsByName(categoryDTO.name())) {
           throw  new EntityExistsException("Category already exists");
       }

       CategoryEntity category = CategoryUtils.convert(categoryDTO);

       categoryRepository.save(category);

       return categoryDTO;
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO){
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("category not found"));

        CategoryUtils.updateCategory(entity, categoryDTO);

        categoryRepository.save(entity);

        return categoryDTO;
    }

    public void deleteCategory(Long id){
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("category not found"));

        categoryRepository.delete(category);
    }

    public void listCategory(){
        categoryRepository.findAll();
    }
}
