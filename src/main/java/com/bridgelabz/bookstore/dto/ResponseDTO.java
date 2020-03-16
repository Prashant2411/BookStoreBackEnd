package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.model.OrderBookDetail;

public class ResponseDTO {

    public OrderBookDetail orderBookDetail;
    public String message;
    public BookDetails bookDetails;

    public ResponseDTO(String message, BookDetails bookDetails) {
        this.message = message;
        this.bookDetails = bookDetails;
    }

    public ResponseDTO(String message, OrderBookDetail orderBookDetail) {
        this.message=message;
        this.orderBookDetail=orderBookDetail;
    }
}
