package com.app.user_service.controller;

import com.app.user_service.dto.UserDTO;
import com.app.user_service.entity.User;
import com.app.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.createUser(userDTO));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar user" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(required = true, name = "id") Long id, @RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.editUser(id, userDTO));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar user" + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(required = true, name = "id") Long id) {
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar user" + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        try{
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar users" + e.getMessage());
        }
    }
}
