package com.bridgelabz.bookstore.enumerator;

import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public enum SortAttribute {
    SORT_BY_RELEVANCE {
        @Override
        public List<BookDetails> getDataSorted(BookStoreRepository bookStoreRepository) {
            return null;
        }
    },
    LOW_TO_HIGH {
        @Override
        public List<BookDetails> getDataSorted(BookStoreRepository bookStoreRepository) {
            return bookStoreRepository.findAll(Sort.by(Sort.Direction.ASC, "bookPrice"));
        }
    },
    HIGH_TO_LOW {
        @Override
        public List<BookDetails> getDataSorted(BookStoreRepository bookStoreRepository) {
            return bookStoreRepository.findAll(Sort.by(Sort.Direction.DESC, "bookPrice"));
        }
    },
    NEWEST_ARRIVALS {
        @Override
        public List<BookDetails> getDataSorted(BookStoreRepository bookStoreRepository) {
            return bookStoreRepository.findAll(Sort.by(Sort.Direction.DESC, "publishingYear"));
        }
    };

    public abstract List<BookDetails> getDataSorted(BookStoreRepository bookStoreRepository);
}
