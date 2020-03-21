package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.model.OrderBookDetail;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import com.bridgelabz.bookstore.repository.OrderBookDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderBookDetailService implements IOrderBookDetailService {

    @Autowired
    OrderBookDetailRepository orderBookDetailRepository;

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Override
    public int addOrderBookSummary(OrderBookDetailDTO... orderBookDetailDTO) {
        int orderId = getOrderId();
        Arrays.stream(orderBookDetailDTO).forEach(value -> {
            Optional<BookDetails> byId = bookStoreRepository.findById(value.bookIds);
            if(value.noOfCopies > byId.get().noOfCopies)
                throw new BookStoreException(BookStoreException.ExceptionType.ORDER_QUANTITY_GREATER_THEN_STOCK,"Order quantity is greater then stock");
        });
        List<OrderBookDetail> collect = Arrays.stream(orderBookDetailDTO).map(value -> {
            OrderBookDetail orderBookDetail = new OrderBookDetail(value);
            orderBookDetail.orderId = orderId;
            return orderBookDetailRepository.save(orderBookDetail);
        }).collect(Collectors.toList());
        updateStock(collect);
        return collect.get(0).orderId;
    }

    private int getOrderId() {
        boolean unique = false;
        int orderId = 0;
        while(!unique){
            orderId = (int) Math.floor(100000 + Math.random() * 900000);
            Optional<OrderBookDetail> byId = orderBookDetailRepository.findById(orderId);
            if( byId.isEmpty())
                unique = true;
        }
        System.out.println("Order Id---------------------> " +orderId);
        return orderId;
    }

    private void updateStock(List<OrderBookDetail> orderBookDetail) {
        orderBookDetail.stream().forEach(value -> {
            bookStoreRepository.updateStock(value.bookIds, value.noOfCopies);
        });
    }
}
