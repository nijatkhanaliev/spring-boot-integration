package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringBootIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntegrationApplication.class, args);
    }

}
