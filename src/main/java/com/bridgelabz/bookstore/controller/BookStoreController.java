package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.enumerator.SortAttribute;
import com.bridgelabz.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/bookcount")
    public int getNoOfStoredBooks()
    { return bookStoreService.getStoredBookCount();
    }

    @GetMapping("/books/image/{imageId}")
    public ResponseEntity getImageUrl(@PathVariable("imageId") String imageId){
        Resource imageResponse = bookStoreService.getImageResponse(imageId);
        return new ResponseEntity(imageResponse, HttpStatus.OK);
    }

    @GetMapping("/bookcount/{attribute}")
    public int getCountOfSearchBooks(@PathVariable String attribute){
        return bookStoreService.getStoredBookCount(attribute);
    }

    @GetMapping("/sortattribute")
    public SortAttribute[] getSortAttribute(){
        SortAttribute[] sortAttribute = bookStoreService.getSortAttribute();
        return sortAttribute;
    }
}
