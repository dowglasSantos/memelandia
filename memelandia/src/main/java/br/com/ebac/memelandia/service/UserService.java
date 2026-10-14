package br.com.ebac.memelandia.service;

import br.com.ebac.memelandia.dto.UserDTO;
import br.com.ebac.memelandia.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserFeign userFeign;

    public UserDTO createUser(UserDTO user){
        UserDTO userDTO = new UserDTO(user.getName(), user.getEmail());

        userFeign.createUser(user);

        return userDTO;
    }

    public UserDTO updateUser(UserDTO user, Long userID) {
        UserDTO userDTO = new UserDTO(user.getName(), user.getEmail());

        userFeign.updateUser(user, userID);

        return userDTO;
    }

    public void deleteUser(Long userID) {
        userFeign.deleteUser(userID);
    }

    public List<UserDTO> listUser() {
        return userFeign.listUser();
    }
}
