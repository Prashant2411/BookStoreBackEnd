package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.service.BookStoreService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class BookStoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookStoreService bookStoreService;

    BookDTO bookDTO;
    Gson gson = new Gson();

    // Search

    @Test
    void givenAttributeToSearchByBookName_whenSearchBooks_shouldReturnSearchedBooks() throws Exception {
        bookDTO = new BookDTO("make me ", "abcd", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        List books = new ArrayList();
        books.add(bookDetails);
        when(bookStoreService.searchBook(any(), anyInt())).thenReturn(books);
        MvcResult mvcResult = this.mockMvc.perform(get("/bookstore/search/abcd/1")).andReturn();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(bookDetails.authorName));
    }

    @Test
    void givenAttributeToSearchByAuthorName_whenSearchBooks_shouldReturnSearchedBooks() throws Exception {
        bookDTO = new BookDTO("make me ", "abcd", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        List books = new ArrayList();
        books.add(bookDetails);
        when(bookStoreService.searchBook(any(), anyInt())).thenReturn(books);
        MvcResult mvcResult = this.mockMvc.perform(get("/bookstore/search/abcd/1")).andReturn();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(bookDetails.authorName));
    }

    @Test
    void givenAttributeToSearch_whenNoBookFound_shouldReturnNoBookFound() throws Exception {
        try {
            when(bookStoreService.searchBook(any(), anyInt())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.NO_BOOK_FOUND, "No Book Found!!"));
            MvcResult mvcResult = this.mockMvc.perform(get("/bookstore/search/zyx/1")).andReturn();
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.NO_BOOK_FOUND, e.type);
        }
    }

    // AllData
    @Test
    void givenPageNumber_whenGetBooks_shouldReturnBooksOnPageNumber() throws Exception {
        bookDTO = new BookDTO("make me", "abc", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDTO bookDTO1 = new BookDTO("xyz", "xyz", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        BookDetails bookDetails1 = new BookDetails(bookDTO1);
        List books = new ArrayList();
        books.add(bookDetails);
        books.add(bookDetails1);
        when(bookStoreService.getAllBooks(anyInt())).thenReturn(books);
        MvcResult mvcResult = this.mockMvc.perform(get("/bookstore/allbooks/1")).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue(contentAsString.contains("make"));
    }

    @Test
    void givenWrongPageNumber_whenGetBooks_shouldThrowMaxPageLimitReachedException() throws Exception {
        try {
            when(bookStoreService.getAllBooks(anyInt())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.MAX_PAGE_LIMIT_REACHED, "Max Page Limit Reached"));
            MvcResult mvcResult = this.mockMvc.perform(get("/bookstore/allbooks/12")).andReturn();
            String contentAsString = mvcResult.getResponse().getContentAsString();
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.MAX_PAGE_LIMIT_REACHED, e.type);
        }
    }
}
