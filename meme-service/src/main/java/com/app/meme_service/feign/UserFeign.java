package com.app.meme_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserFeign {
    @GetMapping("/user/find-by-id/{userID}")
    void verifyUserExists(@PathVariable(name = "userID") Long userID);
}
