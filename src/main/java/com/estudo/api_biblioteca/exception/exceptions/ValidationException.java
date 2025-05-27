package com.estudo.api_biblioteca.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationException extends ErrorResponseException{
    private final List<FieldError> fieldErrors;


    public ValidationException(List<FieldError> fieldErrors) {
        super("Erro de validação", HttpStatus.BAD_REQUEST, "VALIDATION_ERROR");
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
