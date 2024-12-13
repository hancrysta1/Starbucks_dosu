package com.multi.spring2.cart.advice;

import com.multi.spring2.product.exception.AddException;
import com.multi.spring2.product.exception.FindException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class CartAdvice {

    /**
     * 고객 FindException
     * @param e
     * @return
     */
    @ExceptionHandler(FindException.class)
    public ResponseEntity cartFindExceptionHandler(FindException e){
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "text/html;charset=UTF-8");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .headers(headers)
                .body(e.getMessage());
    }

    @ExceptionHandler(AddException.class)
    public ResponseEntity customerAddExceptionHandler(com.multi.spring2.customer.exception.AddException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
