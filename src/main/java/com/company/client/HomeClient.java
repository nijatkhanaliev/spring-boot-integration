package com.company.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "homeClient", url = "${spring.application.url}")
public interface HomeClient {

    @GetMapping("/v1/home")
    String getHome();

}
