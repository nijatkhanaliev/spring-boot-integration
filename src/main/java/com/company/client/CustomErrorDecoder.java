package com.company.client;

import com.company.exceptions.NotFoundException;
import com.company.model.dto.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

import static com.company.exceptions.constants.ErrorCode.RESPONSE_NOT_FOUND;
import static com.company.exceptions.constants.ErrorMessage.RESPONSE_NOT_FOUND_MESSAGE;

@Slf4j
@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String s, Response response) {
        ExceptionResponse exceptionResponse = null;
        if(response==null){
            throw new NotFoundException(RESPONSE_NOT_FOUND_MESSAGE, RESPONSE_NOT_FOUND);
        }
        try (InputStream inputStream = response.body().asInputStream()) {
            exceptionResponse = objectMapper.readValue(inputStream, ExceptionResponse.class);

            return switch (response.status()){
                case 404 -> new NotFoundException(exceptionResponse.getErrorMessage(),
                        exceptionResponse.getErrorCode());

                default -> errorDecoder.decode(s,response);
            };

        } catch (IOException e) {
            log.error("IOException happened, message: {}", e.getMessage());
            return new IOException();
        }
    }
}
