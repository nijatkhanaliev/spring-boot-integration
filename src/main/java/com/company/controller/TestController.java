package com.company.controller;

import com.company.client.HomeClient;
import com.company.client.UserClient;
import com.company.model.dto.request.UserRequest;
import com.company.model.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserClient userClient;
    private final HomeClient homeClient;
    private final RestTemplate restTemplate;

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userClient.getUserById(id);
    }

    @GetMapping("/home")
    public String getHome(@RequestHeader(name = "lang") String lang) {
        return homeClient.getHome();
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest
                                                               userRequest){
        return restTemplate.postForEntity("http://localhost:8585/api/v1/users",
                userRequest, UserResponse.class);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
            URL url = new URL("http://localhost:8585/api/v1/users/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setDoOutput(false);

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_NO_CONTENT){
                return ResponseEntity.status(NO_CONTENT).build();
            }else{
                return ResponseEntity.status(responseCode).build();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        restTemplate.delete("http://localhost:8585/api/v1/users/{id}",id);
//
//         return ResponseEntity.status(NO_CONTENT).build();
    }

}
