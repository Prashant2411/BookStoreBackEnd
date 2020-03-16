package com.bridgelabz.bookstore.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class BookStoreExceptionHandler {

    @ExceptionHandler(value = BookStoreException.class)
    public ResponseEntity<Object> exceptionHandler(BookStoreException book) {
        return new ResponseEntity<>(book.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
