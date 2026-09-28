package com.app.meme_service.service;

import com.app.meme_service.dto.MemeDTO;
import com.app.meme_service.entity.CategoryEntity;
import com.app.meme_service.entity.MemeEntity;
import com.app.meme_service.feign.UserService;
import com.app.meme_service.repository.CategoryRepository;
import com.app.meme_service.repository.MemeRepository;
import com.app.meme_service.utils.MemeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemeService {
    private final MemeRepository memeRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @Autowired
    public MemeService(MemeRepository memeRepository, CategoryRepository repository,  UserService userService) {
       this.memeRepository = memeRepository;
       this.categoryRepository = repository;
       this.userService = userService;
   }

    public MemeDTO createMeme(MemeDTO memeDTO, Long categoryID) {
        CategoryEntity category = categoryRepository.findById(categoryID).orElseThrow(() -> {
            log.error("Category id not found");
            return new IllegalArgumentException();
        });

        userService.verifyUserExists(memeDTO.userID());

        MemeEntity meme = MemeUtils.convert(memeDTO, category);
        memeRepository.save(meme);

        return memeDTO;
    }

    public void updateMeme() {

    }

    public void deleteMeme() {

    }

    public void listMeme() {

    }
}
