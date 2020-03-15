package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.BookDetails;

import java.util.List;

public interface IBookStoreService {
    List<BookDetails> searchBook(String attribute, int pageNumber);

    List<BookDetails> getAllBooks(int pagenumber);
}