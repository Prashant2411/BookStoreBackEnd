package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.model.OrderBookDetail;
import com.bridgelabz.bookstore.repository.OrderBookDetailRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderBookDetailServiceTest {

    @Mock
    OrderBookDetailRepository orderBookDetailRepository;

    @InjectMocks
    OrderBookDetailService orderBookDetailService;

    OrderBookDetailDTO orderBookDetailDTO;

    @Test
    void OrderBookDetailDto_whenDetailAdd_ItShouldOrderReturnBookDetails() {
        orderBookDetailDTO = new OrderBookDetailDTO("7 4 ", "2 3", 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
        OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailRepository.save(any())).thenReturn(orderBookDetail);
        OrderBookDetail orderBookDetail1 = orderBookDetailService.addOrderBookSummary(orderBookDetailDTO);
        Assert.assertEquals( orderBookDetail, orderBookDetail1);
    }
}
