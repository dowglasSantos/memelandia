package com.app.meme_service.utils;

import com.app.meme_service.dto.CategoryDTO;
import com.app.meme_service.entity.CategoryEntity;

import java.time.LocalDateTime;

public final class CategoryUtils {
    private CategoryUtils(){

    };

    public static CategoryEntity convert(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.name());
        categoryEntity.setDescription(categoryDTO.description());
        categoryEntity.setCreatedAt(LocalDateTime.now());

        return categoryEntity;
    }

    public static CategoryEntity updateCategory(CategoryEntity categoryEntity, CategoryDTO categoryDTO) {
        categoryEntity.setName(categoryDTO.name());
        categoryEntity.setDescription(categoryDTO.description());

        return categoryEntity;
    }
}
