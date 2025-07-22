package com.company.exceptions.handler;

import com.company.exceptions.NotFoundException;
import com.company.model.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse
                        .builder()
                        .errorMessage(e.getErrorMessage())
                        .errorCode(e.getErrorCode())
                        .build()
                );
    }

}
