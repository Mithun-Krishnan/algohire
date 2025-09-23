package com.algohire.backend.exception;

public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException(String mes){
        super(mes);
    }
}
