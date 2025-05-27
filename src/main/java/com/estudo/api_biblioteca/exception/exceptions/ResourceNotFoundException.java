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
}
