package com.Quiz.Application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<String>resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String msg=ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

}
