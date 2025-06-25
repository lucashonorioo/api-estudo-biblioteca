package com.estudo.api_biblioteca.exception.error;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;

public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String errorCode;
    private String path;

    public ErrorResponse(){

    }
    public ErrorResponse(Instant timestamp, int status, String error, String errorCode, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
        this.path = path;
    }


    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getPath() {
        return path;
    }
}
