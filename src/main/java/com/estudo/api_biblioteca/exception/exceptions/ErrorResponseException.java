package com.estudo.api_biblioteca.exception.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponseException extends RuntimeException{
    private final HttpStatus status;
    private final String errorCode;


    public ErrorResponseException(HttpStatus status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public ErrorResponseException(String msg, HttpStatus status, String errorCode) {
        super(msg);
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
