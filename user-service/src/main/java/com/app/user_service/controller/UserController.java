package com.app.user_service.controller;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.UserEntity;
import com.app.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.createUser(userDTO));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.editUser(id, userDTO));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable(name = "id") Long id) {
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        try{
            return ResponseEntity.ok(userService.findAllUsers());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable(name = "id") Long id) {
        try{
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable(name = "email") String email) {
        try{
            return ResponseEntity.ok(userService.findByEmail(email));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
