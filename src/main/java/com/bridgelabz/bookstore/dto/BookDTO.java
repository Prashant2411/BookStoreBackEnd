package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookDTO {

    @NotNull
    public String bookName;
    @NotNull
    public String authorName;
    @NotNull
    public double bookPrice;
    @NotNull
    public double noOfCopies;
    public String bookDetail;
    public String bookImageSrc;
    @NotNull
    public Date date;

    public BookDTO(@NotNull String bookName, @NotNull String authorName, @NotNull double bookPrice, @NotNull double noOfCopies, String bookDetail, String bookImageSrc, @NotNull Date date) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPrice = bookPrice;
        this.noOfCopies = noOfCopies;
        this.bookDetail = bookDetail;
        this.bookImageSrc = bookImageSrc;
        this.date = date;
    }
}
