package com.company.service;

import com.company.client.UserClient;
import com.company.model.dto.request.UserRequest;
import com.company.model.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;
    private int i = 0;

    @Scheduled(cron ="* 50 16 ? * TUE" )
    public void createUser(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse("29/12/2004",dateTimeFormatter);

        i++;
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Nicat " + i);
        userRequest.setLastName("Xanaliev " + i);
        userRequest.setBirthDate(birthDate);

        UserResponse userResponse = userClient.createUser(userRequest);
        log.info("UserResponse: { {} }",userResponse);
    }


}
