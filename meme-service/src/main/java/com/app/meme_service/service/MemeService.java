package com.app.meme_service.service;

import com.app.meme_service.dto.MemeDTO;
import com.app.meme_service.entity.CategoryEntity;
import com.app.meme_service.entity.MemeEntity;
import com.app.meme_service.exception.category_exception.CategoryNotFoundException;
import com.app.meme_service.exception.meme_exception.MemeNotFoudException;
import com.app.meme_service.feign.UserFeign;
import com.app.meme_service.repository.CategoryRepository;
import com.app.meme_service.repository.MemeRepository;
import com.app.meme_service.utils.MemeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemeService {
    private final MemeRepository memeRepository;
    private final CategoryRepository categoryRepository;
    private final UserFeign userFeign;

    @Autowired
    public MemeService(MemeRepository memeRepository, CategoryRepository repository,  UserFeign userFeign) {
       this.memeRepository = memeRepository;
       this.categoryRepository = repository;
       this.userFeign = userFeign;
   }

    public MemeDTO createMeme(MemeDTO memeDTO, Long categoryID) {
        log.info("starting meme registration");

        userFeign.verifyUserExists(memeDTO.userID());

        CategoryEntity category = categoryRepository.findById(categoryID).orElseThrow(() -> {
            log.warn("Category not found id={}" + categoryID);
            return new CategoryNotFoundException("category not found");
        });

        MemeEntity meme = MemeUtils.convert(memeDTO, category);

        log.info("Meme registered");
        memeRepository.save(meme);

        return memeDTO;
    }

    public MemeDTO updateMeme(MemeDTO memeDTO, Long memeID) {
        log.info("Starting to update the meme data. memeDTO={}" + memeDTO);

        userFeign.verifyUserExists(memeDTO.userID());

        MemeEntity entity = memeRepository.findById(memeID).orElseThrow(() -> {
            log.warn("meme not found id={}" + memeID);
            throw  new MemeNotFoudException("meme not found");
        });

        MemeEntity editMeme = MemeUtils.edit(memeDTO, entity);

        log.info("Meme successfully updated.");
        memeRepository.save(editMeme);

        return memeDTO;
    }

    public void deleteMeme(Long memeID, Long userID) {
        log.info("deleting meme id={}" + memeID);

        userFeign.verifyUserExists(userID);

        MemeEntity meme = memeRepository.findById(memeID).orElseThrow(() -> {
            log.warn("meme not found id={}" + memeID);
            throw new MemeNotFoudException("meme not found");
        });

        log.info("Meme successfully deleted");
        memeRepository.deleteById(meme.getId());
    }

    public List<MemeEntity> listMeme() {
        log.info("looking for the list of memes");

        List<MemeEntity> memes = memeRepository.findAll();

        log.info("number of memes found []" + memes.size());

        return memes;
    }
}
