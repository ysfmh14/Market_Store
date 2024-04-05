package com.example.market_store.ExceptionHandler;

import com.example.market_store.dto.ResponseErrorDto;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        ResponseErrorDto errorResponse = new ResponseErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EntityAlreadyExisteException.class)
    public ResponseEntity<ResponseErrorDto> handleEntityAlreadyExisteException(EntityAlreadyExisteException ex) {
        ResponseErrorDto errorResponse = new ResponseErrorDto(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
