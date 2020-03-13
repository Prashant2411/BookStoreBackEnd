package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStoreRepository extends JpaRepository<BookDetails,Integer> {
    Optional<BookDetails> findBybookName(String bookName);
}
