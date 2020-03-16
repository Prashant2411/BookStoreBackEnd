package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookStoreServiceTest {

    @Mock
    BookStoreRepository bookStoreRepository;

    @InjectMocks
    BookStoreService bookStoreService;

    BookDTO bookDTO;

    // SearchBook

    @Test
    void givenAttributeToSearchByBookName_whenSearchBooks_shouldReturnSearchedBooks() {
        bookDTO = new BookDTO("make me ", "abcd", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        bookDetails.id = 1;
        List books = new ArrayList();
        books.add(bookDetails);
        when(bookStoreRepository.findByAttribute(any())).thenReturn(books);
        List<BookDetails> abc = bookStoreService.searchBook("xyz", 1);
        Assert.assertEquals(1, abc.size());
    }

    @Test
    void givenAttributeToSearchByAuthorName_whenSearchBooks_shouldReturnSearchedBooks() {
        bookDTO = new BookDTO("make me ", "abcd", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        bookDetails.id = 1;
        List books = new ArrayList();
        books.add(bookDetails);
        when(bookStoreRepository.findByAttribute(any())).thenReturn(books);
        List<BookDetails> abc = bookStoreService.searchBook("xyz", 1);
        Assert.assertEquals(1, abc.size());
    }

    @Test
    void givenAttributeToSearch_whenNoBookFound_shouldReturnNoBookFound() {
        try {
            when(bookStoreRepository.findByAttribute(any())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.NO_BOOK_FOUND, "No Book Found"));
            List<BookDetails> abc = bookStoreService.searchBook("xyz", 1);
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.NO_BOOK_FOUND, e.type);
        }
    }

    // GetAllBook

    @Test
    void givenPageNumber_whenGetBooks_shouldReturnBooksOnThatPage() {
        bookDTO = new BookDTO("make me", "abc", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDTO bookDTO1 = new BookDTO("xyz", "xyz", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        BookDetails bookDetails1 = new BookDetails(bookDTO1);
        List books = new ArrayList();
        books.add(bookDetails);
        books.add(bookDetails1);
        when(bookStoreRepository.getBooks(anyInt(), anyInt())).thenReturn(books);
        List<BookDetails> booksReturned = bookStoreService.getAllBooks(1);
        System.out.println("-------------> " + booksReturned);
        Assert.assertEquals(2, booksReturned.size());
    }

    @Test
    void givenWrongPageNumber_whenGetBook_shouldReturnException() {
        try {
            bookDTO = new BookDTO("make me", "abc", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
            BookDTO bookDTO1 = new BookDTO("xyz", "xyz", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
            BookDetails bookDetails = new BookDetails(bookDTO);
            BookDetails bookDetails1 = new BookDetails(bookDTO1);
            List books = new ArrayList();
            books.add(bookDetails);
            books.add(bookDetails1);
            when(bookStoreRepository.getBooks(anyInt(), anyInt())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.MAX_PAGE_LIMIT_REACHED, "Max Page Limit Reached"));
            List<BookDetails> booksReturned = bookStoreService.getAllBooks(12);
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.MAX_PAGE_LIMIT_REACHED, e.type);
        }
    }
}
