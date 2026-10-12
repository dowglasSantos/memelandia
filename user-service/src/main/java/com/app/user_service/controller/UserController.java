package com.app.user_service.controller;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.UserEntity;
import com.app.user_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.editUser(id, userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
