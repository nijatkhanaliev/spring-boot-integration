package com.company.controller;

import com.company.client.HomeClient;
import com.company.client.UserClient;
import com.company.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserClient userClient;
    private final HomeClient homeClient;

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userClient.getUserById(id);
    }

    @GetMapping("/home")
    public String getHome(@RequestHeader(name = "lang") String lang) {
        return homeClient.getHome();
    }

}
