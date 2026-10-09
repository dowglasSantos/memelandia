package com.app.user_service.service;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.UserEntity;
import com.app.user_service.repository.UserRepository;
import com.app.user_service.utils.UserUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity createUser(UserDTO userDTO) {
        if(userDTO.email()  == null || userDTO.email().isEmpty()) {
            log.warn("Error creating user");
            throw new RuntimeException(userDTO.email());
        }

        UserEntity user =  UserUtils.converter(userDTO);

        return userRepository.save(user);
    }

    public UserEntity editUser(Long id, UserDTO userDTO) {
       UserEntity entity = userRepository.findById(id)
               .orElseThrow(() -> {
                   log.warn("Error editing user, editUser");
                   return new EntityNotFoundException("User not found with id: " + id);
               });

       UserEntity newUser = UserUtils.edit(entity, userDTO);

        return userRepository.save(newUser);
    }

    public UserEntity deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Non-existent user, deleteUser");
                    return new EntityNotFoundException("User not found with id: " + id);
                });

        userRepository.deleteById(id);

        return user;
    }

    public List<UserEntity> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        if (users.isEmpty()) {
            log.warn("no user found, getAllUsers");
        }

        return users;
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Non-existent user, findById");
                    return new EntityNotFoundException("User not found with ID: " + id);
                });
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Non-existent email, findByEmail");
                    return new EntityNotFoundException("User not found with email: " + email);
                });
    }
}
