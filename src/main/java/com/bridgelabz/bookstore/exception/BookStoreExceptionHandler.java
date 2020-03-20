package com.bridgelabz.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class BookStoreExceptionHandler {

    @ExceptionHandler(value = BookStoreException.class)
    public ResponseEntity<Object> exceptionHandler(BookStoreException book) {
        return new ResponseEntity<>(book.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> mismathcExceptionHandler(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>("Invalid Attribute", HttpStatus.BAD_REQUEST);
    }
}
