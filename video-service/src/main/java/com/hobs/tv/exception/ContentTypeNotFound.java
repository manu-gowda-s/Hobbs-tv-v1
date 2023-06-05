package com.hobs.tv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContentTypeNotFound extends RuntimeException {
    public ContentTypeNotFound(String message)
    {
        super(message);
    }
}
