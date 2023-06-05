package com.hobs.tv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoContentFoundForThisViewer extends RuntimeException {
    public NoContentFoundForThisViewer(String message)
    {
        super(message);
    }
}
