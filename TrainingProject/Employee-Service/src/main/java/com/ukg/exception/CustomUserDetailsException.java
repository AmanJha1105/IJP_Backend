package com.ukg.exception;

public class CustomUserDetailsException extends RuntimeException{
    public CustomUserDetailsException(String message){
        super(message);
    }
}

