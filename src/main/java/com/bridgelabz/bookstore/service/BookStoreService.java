package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookStoreService implements IBookStoreService {

    private static final int PER_PAGE_LIMIT = 12;
    int startLimit;
    int endLimit;

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Override
    public List<BookDetails> searchBook(String attribute, int pagenumber) {
        List<BookDetails> byAttribute = bookStoreRepository.findByAttribute(attribute);
        if (byAttribute.isEmpty())
            throw new BookStoreException(BookStoreException.ExceptionType.NO_BOOK_FOUND, "No Book Found");
        setLimits(pagenumber);
        return byAttribute.stream()
                .filter(limit -> limit.id >= this.startLimit && limit.id <= this.endLimit ? true : false)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDetails> getAllBooks(int pageNumber) {
        setLimits(pageNumber);
        List<BookDetails> books = bookStoreRepository.getBooks(startLimit, endLimit);
        if (books.size() == 0)
            throw new BookStoreException(BookStoreException.ExceptionType.MAX_PAGE_LIMIT_REACHED, "Max Page Limit Reached");
        return books;
    }

    @Override
    public int getStoredBookCount() {
        int countOfBooks = bookStoreRepository.getCountOfBooks();
        return countOfBooks;
    }

    private void setLimits(int pageNumber) {
        this.startLimit = ((pageNumber - 1) * PER_PAGE_LIMIT) + 1;
        this.endLimit = (pageNumber * PER_PAGE_LIMIT);
    }
}
