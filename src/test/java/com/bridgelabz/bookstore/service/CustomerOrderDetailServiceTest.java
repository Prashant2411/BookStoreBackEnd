package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.model.OrderBookDetail;
import com.bridgelabz.bookstore.repository.BookStoreRepository;
import com.bridgelabz.bookstore.repository.OrderBookDetailRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerOrderDetailServiceTest {

    @Mock
    OrderBookDetailRepository orderBookDetailRepository;

    @Mock
    BookStoreRepository bookStoreRepository;

    @InjectMocks
    OrderBookDetailService orderBookDetailService;

    OrderBookDetailDTO orderBookDetailDTO;

    @Test
    void OrderBookDetailDto_whenDetailAdd_ItShouldOrderReturnBookDetails() {
        orderBookDetailDTO = new OrderBookDetailDTO(1, 1, 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
        BookDTO bookDTO = new BookDTO("aaaa","aaaa",60.0,5,"qqq","aaaaaa",2010);
        BookDetails bookDetails = new BookDetails(bookDTO);
        OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailRepository.save(any())).thenReturn(orderBookDetail);
        doNothing().when(bookStoreRepository).updateStock(anyInt(), anyInt());
        when(bookStoreRepository.findById(anyInt())).thenReturn(Optional.of(bookDetails));
        int orderBookDetail1 = orderBookDetailService.addOrderBookSummary(orderBookDetailDTO);
        Assert.assertEquals(orderBookDetail.orderId, orderBookDetail1);
    }

    @Test
    void givenMoreQuantityThenStock_whenOrderBook_thenReturnException() {
        try {
            orderBookDetailDTO = new OrderBookDetailDTO(1, 1, 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
            OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
            when(orderBookDetailRepository.findById(anyInt())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.ORDER_QUANTITY_GREATER_THEN_STOCK, "Order quantity grater then stock"));
            int orderBookDetail1 = orderBookDetailService.addOrderBookSummary(orderBookDetailDTO);
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.ORDER_QUANTITY_GREATER_THEN_STOCK,e.type);
        }
    }
}
