package com.estudo.api_biblioteca.exception;

import com.estudo.api_biblioteca.exception.error.ErrorResponse;
import com.estudo.api_biblioteca.exception.exceptions.ErrorResponseException;
import com.estudo.api_biblioteca.exception.exceptions.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ErrorResponse> handleCustomExceptions(ErrorResponseException ex, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                ex.getStatus().value(),
                ex.getMessage(),
                ex.getErrorCode(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(
                Instant.now(),
                status.value(),
                "Dados inválidos.",
                "VALIDATION_ERROR",
                webRequest.getDescription(false)
        );
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrorResponse.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(validationErrorResponse);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                status.value(),
                "Corpo da requisição inválido. Verifique o formato JSON e o tipo de dados.",
                "INVALID_REQUEST_BODY",
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, status);
    }

}
