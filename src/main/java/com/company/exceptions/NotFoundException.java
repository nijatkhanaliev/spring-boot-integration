package com.company.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String errorMessage;
    private final String errorCode;

    public NotFoundException(String msg, String code) {
        super(msg);
        this.errorMessage = msg;
        this.errorCode = code;
    }

}
