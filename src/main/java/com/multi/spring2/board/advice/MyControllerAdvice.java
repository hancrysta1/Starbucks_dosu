package com.multi.spring2.board.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentExceptionHandler(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
