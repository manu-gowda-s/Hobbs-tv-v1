package com.hobs.tv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoContentFoundException extends RuntimeException
{
    private String resourceName;
    private String fieldName;
    private Long fieldValue;

    public NoContentFoundException(String resourceName, String fieldName, Long fieldValue) {

        super(String.format("%s is not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
