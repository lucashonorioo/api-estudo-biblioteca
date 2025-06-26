package com.estudo.api_biblioteca.exception.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ErrorResponseException{
    public ResourceNotFoundException(String resourceName, Long id){
        super(
                String.format("%s com ID %d não encontrado", resourceName, id),
                HttpStatus.NOT_FOUND,
                "RESOURCE_NOT_FOUND"
        );
    }

    public ResourceNotFoundException(String resourceName, String msg){
        super(
                String.format("%s com valor '%s' não encontrado", resourceName, msg),
                HttpStatus.NOT_FOUND,
                "RESOURCE_NOT_FOUND"
        );
    }
}