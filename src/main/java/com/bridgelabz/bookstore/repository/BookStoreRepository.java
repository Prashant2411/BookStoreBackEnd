package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookStoreRepository extends JpaRepository<BookDetails, Integer> {
    Optional<BookDetails> findBybookName(String bookName);

    @Query(value = "select * from book_details where author_name LIKE %:keyword% OR book_name LIKE %:keyword%", nativeQuery = true)
    List<BookDetails> findByAttribute(@Param("keyword") String keyword);

    @Query(value = "select * from book_details where id >= :startLimit AND id <= :endLimit", nativeQuery = true)
    List<BookDetails> getBooks(@Param("startLimit") int startLimit,@Param("endLimit") int endLimit);

    @Query(value = "select count(*) from book_details",nativeQuery = true)
    int getCountOfBooks();
}


