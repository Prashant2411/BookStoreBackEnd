package com.bridgelabz.bookstore.Exception;

public class BookStoreException extends RuntimeException {

    ExceptionType type;
    public enum ExceptionType{
        INVALID_DATA;
    }
    public BookStoreException(ExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}
