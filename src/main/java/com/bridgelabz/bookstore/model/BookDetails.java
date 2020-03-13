package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String bookName;
    public String authorName;
    public double bookPrice;
    public double noOfCopies;
    public String bookDetail;
    public String bookImageSrc;
    public Date date;
}
