package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.enumerator.SortAttribute;
import com.bridgelabz.bookstore.property.FileStorageProperty;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        List<BookDetails> foundList = new ArrayList<>();
        for(int i = startLimit; i <= endLimit && i < byAttribute.size(); i++)
            foundList.add(byAttribute.get(i));
        return foundList;
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
            return bookStoreRepository.getCountOfBooks();
        }
        return bookStoreRepository.getCountOfSearchBooks(attribute[0]);
    }

    @Override
    public SortAttribute[] getSortAttribute() {
       return SortAttribute.values();
    }

    @Override
    public List<BookDetails> getSortedBookData(SortAttribute attribute) {
        List<BookDetails> bookPrice=null;
        if(attribute.equals(SortAttribute.LOW_TO_HIGH))
            bookPrice = bookStoreRepository.findAll(Sort.by(Sort.Direction.ASC, "bookPrice"));
        else if (attribute.equals(SortAttribute.HIGH_TO_LOW))
            bookPrice= bookStoreRepository.findAll(Sort.by(Sort.Direction.DESC, "bookPrice"));
        else if(attribute.equals(SortAttribute.NEWEST_ARRIVALS))
            bookPrice= bookStoreRepository.findAll(Sort.by(Sort.Direction.DESC, "publishingYear"));
        return bookPrice;
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
