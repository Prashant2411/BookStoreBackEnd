package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.model.OrderBookDetail;
import com.bridgelabz.bookstore.repository.OrderBookDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBookDetailService implements IOrderBookDetailService {

    @Autowired
    OrderBookDetailRepository orderBookDetailRepository;

    @Override
    public OrderBookDetail addOrderBookSummary(OrderBookDetailDTO orderBookDetailDTO) {
        OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
        OrderBookDetail data = orderBookDetailRepository.save(orderBookDetail);
        return data;
    }

}
