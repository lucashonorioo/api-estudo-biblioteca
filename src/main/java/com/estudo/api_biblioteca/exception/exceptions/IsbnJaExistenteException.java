package com.estudo.api_biblioteca.exception.exceptions;

public class IsbnJaExistenteException extends BusinessException{
    public IsbnJaExistenteException(String msg) {
        super("O ISBN '" + msg + "' já existe.");
    }
}
