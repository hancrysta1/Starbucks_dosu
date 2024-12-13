package com.multi.spring2.order.advice;


import com.multi.spring2.order.exception.AddException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderAdvice {

    /**
     * AddException
     * @param e
     * @return
     */
    @ExceptionHandler(AddException.class)
    public ResponseEntity orderAddException(AddException e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
