package com.app.meme_service.category_test;

import com.app.meme_service.dto.CategoryDTO;
import com.app.meme_service.entity.CategoryEntity;
import com.app.meme_service.repository.CategoryRepository;
import com.app.meme_service.service.CategoryService;
import com.app.meme_service.utils.CategoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void createCategoryTest() {
        CategoryDTO category = new CategoryDTO("Gatos", "Coletânia de memes de gatos");

        CategoryEntity entity = CategoryUtils.convert(category);


        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(entity);

        categoryService.createCategory(category);

        ArgumentCaptor<CategoryEntity> captor = ArgumentCaptor.forClass(CategoryEntity.class);

        verify(categoryRepository).save(captor.capture());

        CategoryEntity categoryCaptor = captor.getValue();

        assertEquals("Gatos", categoryCaptor.getName());
        assertEquals("Coletânia de memes de gatos", categoryCaptor.getDescription());
    }

    @Test
    void updateCategory() {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(1L);
        entity.setName("Editando Teste");
        entity.setCreatedAt(LocalDateTime.now());
        entity.setDescription("Teste de update das categorias");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(entity));

        when(categoryRepository.save(any(CategoryEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CategoryDTO categoryDTO = new CategoryDTO("Testando e editando", "Paralelepipedo");

        categoryService.updateCategory(1L, categoryDTO);

        ArgumentCaptor<CategoryEntity> captor = ArgumentCaptor.forClass(CategoryEntity.class);
        verify(categoryRepository).save(captor.capture());

        CategoryEntity newCategory = captor.getValue();

        assertEquals(entity.getId(), newCategory.getId());
        assertEquals(categoryDTO.name(), newCategory.getName());
    }

    @Test
    void deleteCategory() {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(1L);
        entity.setName("Editando Teste");
        entity.setCreatedAt(LocalDateTime.now());
        entity.setDescription("Teste de update das categorias");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(entity));

        categoryService.deleteCategory(1L);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(categoryRepository).deleteById(captor.capture());

        assertEquals(captor.getValue(), entity.getId());
    }

    @Test
    void listCategory() {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(1L);
        entity.setName("Editando Teste");
        entity.setCreatedAt(LocalDateTime.now());
        entity.setDescription("Teste de update das categorias");

        List<CategoryEntity> listCategory = new ArrayList<>();
        listCategory.add(entity);

        when(categoryRepository.findAll()).thenReturn(listCategory);

        List<CategoryEntity> findList = categoryService.listCategory();
        verify(categoryRepository).findAll();

        assertEquals(listCategory, findList);
    }
}
