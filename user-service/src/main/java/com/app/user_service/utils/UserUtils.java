package com.app.user_service.utils;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;


public final class UserUtils {
    private UserUtils() {
    }

    public static User getDateToDTO(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setRegistrationDate(LocalDateTime.now());

        return user;
    }

    public static User editUserData(User user, UserDTO userDTO) {
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());

        return user;
    }
}
