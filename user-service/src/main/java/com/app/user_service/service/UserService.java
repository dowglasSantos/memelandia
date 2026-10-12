package com.app.user_service.service;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.UserEntity;
import com.app.user_service.exception.user_exception.EmailAlreadyExistsException;
import com.app.user_service.exception.user_exception.EmailNotFoundException;
import com.app.user_service.exception.user_exception.UserNotFoundException;
import com.app.user_service.repository.UserRepository;
import com.app.user_service.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        userRepository.findByEmail(userDTO.email()).ifPresent(user -> {
            log.warn("EMAIL ALREADY IN USE: {}", userDTO.email());
            throw new EmailAlreadyExistsException(
                    "EMAIL ALREADY IN USE WITH E-MAIL " + userDTO.email()
            );
        });

        UserEntity user =  UserUtils.converter(userDTO);
        userRepository.save(user);

        return userDTO;
    }

    public UserDTO editUser(Long id, UserDTO userDTO) {
       UserEntity entity = userRepository.findById(id)
               .orElseThrow(() -> {
                   log.warn("Error editing user, editUser");
                   return new UserNotFoundException("User not found with id: " + id);
               });

       UserEntity newUser = UserUtils.edit(entity, userDTO);
       userRepository.save(newUser);

       return userDTO;
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> {
                    log.warn("Non-existent user");
                    return new UserNotFoundException("User not found with id: " + id);
                });

        userRepository.deleteById(id);
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Non-existent user, findById");
                    return new UserNotFoundException("User not found with ID: " + id);
                });
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Non-existent email, findByEmail");
                    return new EmailNotFoundException("User not found with email: " + email);
                });
    }
}
