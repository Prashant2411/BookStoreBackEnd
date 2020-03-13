package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.model.BookDetails;

public class ResponseDTO {

    public String message;
    public BookDetails bookDetails;

    public ResponseDTO(String message, BookDetails bookDetails) {
        this.message = message;
        this.bookDetails = bookDetails;
    }
}
