package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
@CrossOrigin("*")
public class BookStoreController {

    @Autowired
    BookStoreService bookStoreService;

    @GetMapping("/search/{attribute}/{pagenumber}")
    public List<BookDetails> searchBooks(@PathVariable String attribute, @PathVariable int pagenumber) {
        return bookStoreService.searchBook(attribute, pagenumber);
    }

    @GetMapping("/allbooks/{pagenumber}")
    public List<BookDetails> getAllBooks(@PathVariable int pagenumber) {
        return bookStoreService.getAllBooks(pagenumber);
    }
}
