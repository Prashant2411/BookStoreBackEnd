package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {

    @Autowired
    BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<ResponseDTO> addBook(@Valid @RequestBody BookDTO bookDto, BindingResult result) throws BookStoreException {
        System.out.println(bookDto);
        if(result.hasErrors()){
            throw new BookStoreException(BookStoreException.ExceptionType.INVALID_DATA,"Invalid Data");
        }
        BookDetails bookDetails = bookService.addBook(bookDto);
        ResponseDTO userData = new ResponseDTO("Book Added Successfully", bookDetails);
        return new ResponseEntity<ResponseDTO>(userData, HttpStatus.OK);
    }
}
