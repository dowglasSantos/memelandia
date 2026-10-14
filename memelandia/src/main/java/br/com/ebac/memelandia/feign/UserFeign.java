package br.com.ebac.memelandia.feign;

import br.com.ebac.memelandia.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user", url = "http://localhost:8081/user")
public interface UserFeign {
    @PostMapping()
    void createUser(@RequestBody UserDTO user);

    @PutMapping("/{userID}")
    void updateUser(@RequestBody UserDTO user, @PathVariable(name = "userID") Long userID);

    @DeleteMapping("/delete/{userID}")
    void deleteUser(@PathVariable(name = "userID") Long userID);

    @GetMapping("/list")
    List<UserDTO> listUser();
}
