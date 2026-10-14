package br.com.ebac.memelandia.controllers;

import br.com.ebac.memelandia.dto.UserDTO;
import br.com.ebac.memelandia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @PutMapping("/{userID}")
    public UserDTO updateUser(@RequestBody UserDTO user, @PathVariable(name = "userID") Long userID) {
        return userService.updateUser(user, userID);
    }

    @DeleteMapping("/{userID}")
    public void deleteUser(@PathVariable(name = "userID") Long userID) {
        userService.deleteUser(userID);
    }

    @GetMapping()
    public List<UserDTO> listUser() {
        return userService.listUser();
    }
}
