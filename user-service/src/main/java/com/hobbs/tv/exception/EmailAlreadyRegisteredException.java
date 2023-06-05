package com.hobbs.tv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyRegisteredException extends RuntimeException
{
    private String message;

    public EmailAlreadyRegisteredException(String message){
        super(message);
    }
}
