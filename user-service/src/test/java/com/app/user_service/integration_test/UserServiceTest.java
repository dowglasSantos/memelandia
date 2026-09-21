package com.app.user_service.integration_test;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.UserEntity;
import com.app.user_service.repository.UserRepository;
import com.app.user_service.service.UserService;
import com.app.user_service.utils.UserUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser() {
        UserDTO userDTO = new UserDTO("huemay", "teste@mocki.com");
        UserEntity user = UserUtils.converter(userDTO);

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        userService.createUser(userDTO);

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository).save(captor.capture());

        UserEntity savedUser = captor.getValue();

        assertEquals("huemay", savedUser.getName());
        assertEquals("teste@mocki.com", savedUser.getEmail());
    }

    @Test
    void editUser() {
       UserEntity user = new UserEntity(
               10L,
               "huemay",
               "huemay@gmail.com",
               LocalDateTime.now()
       );

       when(userRepository.findById(10L)).thenReturn(java.util.Optional.of(user));
       when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

       UserDTO editUser = new UserDTO("huemay edit", "test@mock.com");

       UserEntity newUser =  userService.editUser(10L, editUser);

       assertEquals(user.getId(), newUser.getId());
       assertEquals(editUser.name(), newUser.getName());
    }

    @Test
    void deleteUser() {
        UserEntity user = new UserEntity(
                10L,
                "huemay",
                "huemay@gmail.com",
                LocalDateTime.now()
        );

        when(userRepository.findById(10L)).thenReturn(java.util.Optional.of(user));

        userService.deleteUser(user.getId());

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository).deleteById(captor.capture());

        Long idCaptor = captor.getValue();

        assertEquals(user.getId(), idCaptor);
    }

    @Test
    void findAllUsers() {
        UserEntity user = new UserEntity(
                10L,
                "huemay",
                "huemay@gmail.com",
                LocalDateTime.now()
        );

        List<UserEntity> listUser = new ArrayList<>();

        listUser.add(user);

        when(userRepository.findAll()).thenReturn(listUser);

        List<UserEntity> findList = userService.findAllUsers();

        verify(userRepository).findAll();

        assertEquals(listUser, findList);
    }

    @Test
    void findById() {
        UserEntity user = new UserEntity(
                10L,
                "huemay",
                "huemay@gmail.com",
                LocalDateTime.now()
        );

        when(userRepository.findById(10L)).thenReturn(java.util.Optional.of(user));

        UserEntity findUser = userService.findById(10L);

        verify(userRepository).findById(10L);

        assertEquals(user.getId(), findUser.getId());
    }

    @Test
    void findByEmail() {
        UserEntity user = new UserEntity(
                10L,
                "huemay",
                "huemay@gmail.com",
                LocalDateTime.now()
        );

        when(userRepository.findByEmail("huemay@gmail.com")).thenReturn(java.util.Optional.of(user));

        UserEntity findUser = userService.findByEmail("huemay@gmail.com");

        verify(userRepository).findByEmail("huemay@gmail.com");

        assertEquals(user.getId(), findUser.getId());
    }
}



















