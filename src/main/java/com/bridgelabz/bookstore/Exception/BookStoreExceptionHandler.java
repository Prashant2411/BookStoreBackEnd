package com.bridgelabz.bookstore.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class BookStoreExceptionHandler {
    @ExceptionHandler(value = BookStoreException.class)
    public ResponseEntity<Object> exceptionHandler(BookStoreException book) {
        return new ResponseEntity<>(book.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<Object> SQLIntegretityException(SQLIntegrityConstraintViolationException e) {
//        return new ResponseEntity<>("BOOK ALREADY EXIST", HttpStatus.BAD_REQUEST);
//    }
}
