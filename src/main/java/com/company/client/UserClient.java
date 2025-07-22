package com.company.client;

import com.company.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userClient", url = "${spring.application.url}")
public interface UserClient {

    @GetMapping("/v1/users/{id}")
    UserDto getUserById(@PathVariable Long id);

}
