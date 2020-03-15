package com.bridgelabz.bookstore.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BookDTO {

    @NotNull
    @Length(min = 2, max = 20, message = "Invalid Book Name")
    public String bookName;
    @NotNull
    @Pattern(regexp = "^[a-z,A-Z_]*$")
    public String authorName;
    @NotNull
    public double bookPrice;
    @NotNull
    public double noOfCopies;
    public String bookDetail;
    public String bookImageSrc;
    @NotNull
    @Range(min = 1000, max = 2020, message = "Invalid Publishing Year")
    public int publishingYear;

    public BookDTO(@NotNull String bookName, @NotNull String authorName,
                   @NotNull double bookPrice, @NotNull double noOfCopies,
                   String bookDetail, String bookImageSrc, @NotNull @Length int publishingYear) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPrice = bookPrice;
        this.noOfCopies = noOfCopies;
        this.bookDetail = bookDetail;
        this.bookImageSrc = bookImageSrc;
        this.publishingYear = publishingYear;
    }
}
