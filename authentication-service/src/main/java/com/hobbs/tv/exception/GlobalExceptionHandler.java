package com.hobbs.tv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(UserIDNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlingUserNotFoundException(UserIDNotFoundException exception){

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

}
