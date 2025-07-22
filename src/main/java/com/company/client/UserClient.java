package com.company.client;

import com.company.model.dto.request.UserRequest;
import com.company.model.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userClient", url = "${spring.application.url}")
public interface UserClient {

    @GetMapping("/v1/users/{id}")
    UserResponse getUserById(@PathVariable Long id);

    @PostMapping("/v1/users")
    UserResponse createUser(@Valid @RequestBody UserRequest userRequest);

}
