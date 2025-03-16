package com.group2.theminimart.util;

import java.time.LocalDateTime;
import com.group2.theminimart.dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
  // TODO add logging using SL4J

  // Handle general exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    // log exception, logger.error..
    ErrorResponse errorResponse = new ErrorResponse("Something went wrong. Contact support for help.",
        LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
