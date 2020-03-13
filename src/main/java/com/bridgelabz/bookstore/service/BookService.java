package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Override
    public BookDetails addBook(BookDTO bookDTO) {
        BookDetails bookDetails = new BookDetails(bookDTO);
        Optional<BookDetails> byBookName = bookStoreRepository.findBybookName(bookDTO.bookName);
        if (byBookName.isPresent()) {
            bookDetails.setNoOfCopies(byBookName.get().noOfCopies + bookDTO.noOfCopies);
            bookDetails.id = byBookName.get().id;
        }
        BookDetails info = bookStoreRepository.save(bookDetails);
        return info;
    }
}
