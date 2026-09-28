package com.app.meme_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "http://localhost:8081/user/find-by-id"
)
public interface UserService {
    @GetMapping("{userID}")
    void verifyUserExists(@PathVariable(name = "userID") Long userID);
}
