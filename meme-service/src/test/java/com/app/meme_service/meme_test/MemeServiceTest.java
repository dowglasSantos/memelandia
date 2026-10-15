package com.app.meme_service.meme_test;

import com.app.meme_service.dto.MemeDTO;
import com.app.meme_service.dto.UserDTO;
import com.app.meme_service.entity.CategoryEntity;
import com.app.meme_service.entity.MemeEntity;
import com.app.meme_service.feign.UserFeign;
import com.app.meme_service.repository.CategoryRepository;
import com.app.meme_service.repository.MemeRepository;
import com.app.meme_service.service.MemeService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemeServiceTest {
    @Mock
    MemeRepository memeRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    UserFeign userFeign;

    @InjectMocks
    MemeService memeService;

    @Test
    void createMeme() {
        MemeDTO memeDTO = new MemeDTO(
                "meme test",
                "meme descrição",
                "http://www.google.com",
                1L
        );

        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("Humor");

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        doNothing().when(userFeign).verifyUserExists(1L);

        when(memeRepository.save(any(MemeEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MemeDTO result = memeService.createMeme(memeDTO, 1L);

        ArgumentCaptor<MemeEntity> captor =
                ArgumentCaptor.forClass(MemeEntity.class);

        verify(memeRepository).save(captor.capture());

        MemeEntity memeCaptor = captor.getValue();

        assertEquals(memeDTO.name(), memeCaptor.getName());
        assertEquals(memeDTO.description(), memeCaptor.getDescription());

        assertEquals(category.getId(), memeCaptor.getCategory().getId());

        assertEquals(memeDTO, result);

        verify(categoryRepository).findById(1L);
        verify(userFeign).verifyUserExists(1L);
    }

    @Test
    void updateMeme() {
        MemeEntity meme = new MemeEntity();
        meme.setId(1L);
        meme.setName("Os manos da oeste");
        meme.setDescription("É os guri da catequeze");

        when(memeRepository.findById(1L)).thenReturn(Optional.of(meme));
        when(memeRepository.save(any(MemeEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MemeDTO memeDTO = new MemeDTO("Os guri tão por nada", "É os guri da catequeze 2.0", "4", 1L);

        memeService.updateMeme(memeDTO, 1L);

        ArgumentCaptor<MemeEntity> captor = ArgumentCaptor.forClass(MemeEntity.class);
        verify(memeRepository).save(captor.capture());

        MemeEntity testMeme = captor.getValue();

        assertEquals(memeDTO.name(), testMeme.getName());
        assertEquals(testMeme.getId(), meme.getId());
    }

    @Test
    void deleteMeme() {
        MemeEntity meme = new MemeEntity();
        meme.setId(1L);
        meme.setName("Os manos da oeste");
        meme.setDescription("É os guri da catequeze");

        when(memeRepository.findById(1L))
                .thenReturn(Optional.of(meme));

        doNothing().when(userFeign).verifyUserExists(1L);

        UserDTO userDTO = new UserDTO(1L, "huemay", "huemay@123");

        memeService.deleteMeme(1L, userDTO.id());

        verify(memeRepository).deleteById(1L);
        verify(memeRepository).findById(1L);
        verify(userFeign).verifyUserExists(1L);
    }

    @Test
    void listMeme() {
        MemeEntity meme = new MemeEntity();
        meme.setId(1L);
        meme.setName("Os manos da oeste");
        meme.setDescription("É os guri da catequeze");

        List<MemeEntity> listMeme= new ArrayList<>();
        listMeme.add(meme);

        when(memeRepository.findAll()).thenReturn(listMeme);

        List<MemeEntity> findList = memeService.listMeme();
        verify(memeRepository).findAll();

        assertEquals(listMeme, findList);
    }
}
