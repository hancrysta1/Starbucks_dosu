package com.multi.spring2.product.advice;

import com.multi.spring2.product.exception.FindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ProductAdvice {


    /**
     * 상품 FindException
     * @param e
     * @return
     */
    @ExceptionHandler(FindException.class)
    public ResponseEntity FindExceptionHandler(FindException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
