package com.app.meme_service.utils;

import com.app.meme_service.dto.MemeDTO;
import com.app.meme_service.entity.MemeEntity;
import com.app.meme_service.entity.CategoryEntity;

import java.time.LocalDateTime;

public final class MemeUtils {
    private MemeUtils() {};

    public static MemeEntity convert(MemeDTO memeDTO, CategoryEntity category) {
        MemeEntity memeEntity = new MemeEntity();

        memeEntity.setName(memeDTO.name());
        memeEntity.setDescription(memeDTO.description());
        memeEntity.setMemeUrl(memeDTO.memeUrl());
        memeEntity.setCategory(category);
        memeEntity.setUserID(memeDTO.userID());
        memeEntity.setCreatedAt(LocalDateTime.now());

        return memeEntity;
    }

    public static MemeEntity edit(MemeDTO meme, MemeEntity entity) {
        entity.setName(meme.name());
        entity.setDescription(meme.description());
        entity.setMemeUrl(meme.memeUrl());

        return entity;
    }
}
