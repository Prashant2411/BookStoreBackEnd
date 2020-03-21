package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.model.OrderBookDetail;

public interface IOrderBookDetailService {

    int addOrderBookSummary(OrderBookDetailDTO... orderBookDetailDTO);

}
