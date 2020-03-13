package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookStoreServiceTest {

    @Mock
    BookStoreRepository bookStoreRepository;

    @InjectMocks
    BookService bookService;

    BookDTO bookDTO;

    @Test
    void givenBookDTO_whenBooksStore_ItShouldReturnBookDetails() {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        when(bookStoreRepository.save(any())).thenReturn(bookDetails);
        BookDetails bookDetails1 = bookService.addBook(bookDTO);
        Assert.assertEquals(bookDetails,bookDetails1);
    }

    @Test
    void givenSameBookDTO_whenBooksStore_ItShouldReturnBookDetails() {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        BookDetails bookDetails = new BookDetails(bookDTO);
        when(bookStoreRepository.save(any())).thenReturn(bookDetails);
        when(bookStoreRepository.findBybookName(bookDTO.bookName)).thenReturn(Optional.of(bookDetails));
        BookDetails bookDetails2 = bookService.addBook(bookDTO);
        Assert.assertEquals(bookDetails,bookDetails2);
    }
}
