package com.app.user_service.service;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.User;
import com.app.user_service.repository.UserRepository;
import com.app.user_service.utils.UserUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user =  UserUtils.getDateToDTO(userDTO);

        return userRepository.save(user);
    }

    public User editUser(Long id, UserDTO userDTO) {
       User entity = userRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException(User.class.getName()));

       User newUser = UserUtils.editUserData(entity, userDTO);

        return userRepository.save(newUser);
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class.getName()));

        userRepository.deleteById(id);

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        users.forEach(System.out::println);

        return users;
    }
}
