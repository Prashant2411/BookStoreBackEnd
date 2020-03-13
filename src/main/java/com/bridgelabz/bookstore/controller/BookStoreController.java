package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {

    @Autowired
    BookService bookService;

    @PostMapping("/addbook")
    public void addBook(@RequestBody BookDTO bookDto){
        bookService.addBook(bookDto);
    }

}
