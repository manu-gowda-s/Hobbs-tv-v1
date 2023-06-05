package com.hobs.tv.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails
{
    private LocalDateTime timestamp;
    private String message;
    private String errorResponse;
    private String errorCode;
}
