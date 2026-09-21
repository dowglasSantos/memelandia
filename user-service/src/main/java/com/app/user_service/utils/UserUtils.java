package com.app.user_service.utils;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.UserEntity;

import java.time.LocalDateTime;


public final class UserUtils {
    private UserUtils() {
    }

    public static UserEntity converter(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setRegistrationDate(LocalDateTime.now());

        return user;
    }

    public static UserEntity edit(UserEntity user, UserDTO userDTO) {
        user.setId(user.getId());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setRegistrationDate(user.getRegistrationDate());

        return user;
    }
}
