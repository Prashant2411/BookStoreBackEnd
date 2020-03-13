package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookDetails;

public interface IBookService {

     BookDetails addBook(BookDTO bookDTO);
}
