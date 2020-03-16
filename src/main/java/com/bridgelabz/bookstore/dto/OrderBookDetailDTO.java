package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OrderBookDetailDTO {

    @NotNull
    public String bookIds;


    @NotNull
    public String noOfCopies;

    @NotNull
    public Double orderPrice;

    @NotNull
    @Pattern(regexp ="^[a-zA-Z]{3,20}$",message = "Enter Valid Name")
    public String customerName;

    @NotNull
    @Pattern(regexp = "^[5,9]{1}[0-9]{9}$",message = "Enter Valid Mobile Number")
    public String mobileNo;

    @NotNull
    @Pattern(regexp = "^[0-9]{6}$",message = "Enter Valid Pincode")
    public String pincode;

    @NotNull
    @Pattern(regexp = "^[a-zA-z0-9]+$", message = "Enter Valid Locality")
    public String locality;

    @NotNull
    public String address;

    @NotNull
    @Pattern(regexp = "^[a-zA-z]+$", message = "Enter Valid City")
    public String city;

    @NotNull
    @Pattern(regexp ="^[a-zA-Z0-9]+$",message = "Enter Valid LandMark")
    public String landmark;

    public String type;

    public OrderBookDetailDTO(String bookIds, String noOfCopies, Double orderPrice, String customerName, String mobileNo, String pincode, String locality, String address, String city, String landmark, String type) {
        this.bookIds = bookIds;
        this.noOfCopies = noOfCopies;
        this.orderPrice = orderPrice;
        this.customerName = customerName;
        this.mobileNo = mobileNo;
        this.pincode = pincode;
        this.locality = locality;
        this.address = address;
        this.city = city;
        this.landmark = landmark;
        this.type = type;
    }
}
