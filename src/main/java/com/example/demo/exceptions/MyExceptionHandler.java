package com.example.demo.exceptions;

import com.example.demo.exceptions.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> BadRequestException(Exception e){
        e.getStackTrace();
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFoundException(Exception e){
        e.getStackTrace();
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> InternalServerErrorException(Exception e){
        e.getStackTrace();
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}