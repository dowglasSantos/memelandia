package br.com.ebac.memelandia.feign;

import br.com.ebac.memelandia.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user", url = "${user.service.url}")
public interface UserFeign {
    @PostMapping("/user")
    void createUser(@RequestBody UserDTO user);

    @PutMapping("/user/{userID}")
    void updateUser(@RequestBody UserDTO user, @PathVariable(name = "userID") Long userID);

    @DeleteMapping("/user/delete/{userID}")
    void deleteUser(@PathVariable(name = "userID") Long userID);

    @GetMapping("/user/list")
    List<UserDTO> listUser();
}
