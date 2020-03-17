package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.property.FileStorageProperty;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookStoreService implements IBookStoreService {

    private static final int PER_PAGE_LIMIT = 12;
    int startLimit;
    int endLimit;

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Autowired
    FileStorageProperty fileStorageProperty;

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
    public int getStoredBookCount(String... attribute) {
        if(attribute.length==0) {
            int countOfBooks = bookStoreRepository.getCountOfBooks();
            return countOfBooks;
        }
        int countOfSearchBooks = bookStoreRepository.getCountOfSearchBooks(attribute[0]);
        return countOfSearchBooks;
    }

    private void setLimits(int pageNumber) {
        this.startLimit = ((pageNumber - 1) * PER_PAGE_LIMIT) + 1;
        this.endLimit = (pageNumber * PER_PAGE_LIMIT);
    }

    public Resource getImageResponse(String imageId) {
        Path path = Paths.get(this.fileStorageProperty.getUploadDir() + imageId);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        }catch (MalformedURLException e){
            throw new BookStoreException(BookStoreException.ExceptionType.INVALID_FILE_PATH,"Invalid_Path");
        }
        return resource;
    }
}
