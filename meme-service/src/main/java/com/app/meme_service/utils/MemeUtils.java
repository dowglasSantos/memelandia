package com.app.meme_service.utils;

import com.app.meme_service.dto.MemeDTO;
import com.app.meme_service.entity.MemeEntity;
import com.app.meme_service.entity.CategoryEntity;

import java.time.LocalDateTime;

public final class MemeUtils {
    private MemeUtils() {};

    public static MemeEntity convert(MemeDTO memeDTO, CategoryEntity category) {
        MemeEntity memeEntity = new MemeEntity();

        memeEntity.setCategory(category);
        memeEntity.setURL(memeDTO.URL());
        memeEntity.setName(memeDTO.name());
        memeEntity.setUserEntityId(memeDTO.userID());
        memeEntity.setCreatedAt(LocalDateTime.now());
        memeEntity.setDescription(memeDTO.description());

        return memeEntity;
    }
}
