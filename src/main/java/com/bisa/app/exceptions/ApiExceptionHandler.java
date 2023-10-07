package com.bisa.app.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiError> handlerException(
      NotFoundException notFoundException,
      HttpServletRequest request
  ) {
    ApiError apiError = ApiError.builder()
        .path(request.getRequestURI())
        .message(notFoundException.getMessage())
        .statusCode(HttpStatus.NOT_FOUND.value())
        .localDateTime(getLocalDateTime())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(apiError);
  }

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<ApiError> handlerException(
      DuplicateResourceException duplicateResourceException,
      HttpServletRequest request
  ) {
    ApiError apiError = ApiError.builder()
        .path(request.getRequestURI())
        .message(duplicateResourceException.getMessage())
        .statusCode(HttpStatus.CONFLICT.value())
        .localDateTime(getLocalDateTime())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(apiError);
  }

  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<ApiError> handlerException(
      InvalidDataException invalidDataException,
      HttpServletRequest request
  ) {
    ApiError apiError = ApiError.builder()
        .path(request.getRequestURI())
        .message(invalidDataException.getMessage())
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .localDateTime(getLocalDateTime())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(apiError);
  }

  private String getLocalDateTime() {
    return LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
  }
}
