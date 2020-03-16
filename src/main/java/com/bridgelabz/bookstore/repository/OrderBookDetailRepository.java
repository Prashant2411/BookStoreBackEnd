package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.OrderBookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookDetailRepository extends JpaRepository<OrderBookDetail ,Integer> {

}
