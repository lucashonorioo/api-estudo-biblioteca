package com.estudo.api_biblioteca.exception.exceptions;

import com.estudo.api_biblioteca.exception.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public class BusinessException extends ErrorResponseException {
   public BusinessException(String msg){
       super(msg, HttpStatus.UNPROCESSABLE_ENTITY, "BUSINESS_RULE_VIOLATION");
   }

}
