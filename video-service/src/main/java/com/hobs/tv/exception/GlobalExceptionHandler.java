package com.hobs.tv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(NoContentFoundException.class)
    public ResponseEntity<ErrorDetails> handlingContentNotFoundException(NoContentFoundException ex, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false),
                "NO_CONTENT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContentTypeNotFound.class)
    public ResponseEntity<ErrorDetails> handlingContentTypeNotFound(ContentTypeNotFound ex, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false),
                "CONTENT_TYPE_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentFoundForThisViewer.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlingNoContentFoundForThisViewer(NoContentFoundForThisViewer foundForThisViewer,
                                                                            WebRequest request)
    {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Path", request.getDescription(false));
        errorDetails.put("Error Message", foundForThisViewer.getMessage());
        errorDetails.put("Status Code", "NO_CONTENT_FOUND_FOR_THIS_VIEWER");
        return errorDetails;
    }
}
