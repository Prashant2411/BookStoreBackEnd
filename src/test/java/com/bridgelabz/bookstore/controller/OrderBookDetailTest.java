package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.model.OrderBookDetail;
import com.bridgelabz.bookstore.service.OrderBookDetailService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderBookDetailTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderBookDetailService orderBookDetailService;

    Gson gson = new Gson();
    OrderBookDetailDTO orderBookDetailDTO;

    @Test
    void givenRequest_whenGetResponse_ItShouldReturnStatusOk() throws Exception {
        OrderBookDetailDTO[] orderBookDetail = new OrderBookDetailDTO[2];
        orderBookDetail[0] = new OrderBookDetailDTO(7, 2, 6000.0, "aaaa", "5234543212", "123456", "aaaa", "aaaaaaaa", "aaaaa", "aaaaaa", "aaaa");
        orderBookDetail[1] = new OrderBookDetailDTO(3, 2, 6000.0, "aaaa", "5234543212", "123456", "aaaa", "aaaaaaaa", "aaaaa", "aaaaaa", "aaaa");
        String jsonDto = gson.toJson(orderBookDetail);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(121212);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("-------------------------------" + mvcResult.getResponse().getStatus());
        Assert.assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    void givenRequest_WhenGetResponse_ItsResponseShouldReturnCorrect() throws Exception {
        orderBookDetailDTO = new OrderBookDetailDTO(1, 2, 6000.0, "pugk", "9312345674", "400086", "t", "fgbhjn", "cfg", "gvbh", "vg");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(123456);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("-------------------------------" + mvcResult.getResponse().getStatus());
        Assert.assertEquals("123456", content);
    }

    @Test
    void givenWrongUrl_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        orderBookDetailDTO = new OrderBookDetailDTO(7, 2, 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(123456);
        MvcResult mvcResult = this.mockMvc.perform(post("/  bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println("-------------------------------" + mvcResult.getResponse().getStatus());
        Assert.assertEquals(404, status);
    }

    @Test
    void givenPutRequestInsteadOfPost_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        orderBookDetailDTO = new OrderBookDetailDTO(7, 2, 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(123456);
        MvcResult mvcResult = this.mockMvc.perform(put("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println("-------------------------------" + mvcResult.getResponse().getStatus());
        Assert.assertEquals(405, status);
    }

    @Test
    void givenAnotherContentType_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        orderBookDetailDTO = new OrderBookDetailDTO(7, 2, 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(345671);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_ATOM_XML)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println("-------------------------------" + mvcResult.getResponse().getStatus());
        Assert.assertEquals(415, status);
    }

    @Test
    void givenRequestWithoutConvertItToJson_WhenGetResponse_ItsStatusShouldReturnBad() throws Exception {
        orderBookDetailDTO = new OrderBookDetailDTO(7, 2, 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
        OrderBookDetail orderBookDetail = new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(123456);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(String.valueOf(orderBookDetail))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("-------------------------------" + mvcResult.getResponse().getStatus());
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    void givenRequestToController_WhenWrongRequestData_thenShouldThrowException() throws Exception {
        try {
            orderBookDetailDTO = new OrderBookDetailDTO(7, 2, 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk", "vghjnkml");
            String jsonDto = gson.toJson(orderBookDetailDTO);
            when(orderBookDetailService.addOrderBookSummary(any())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.INVALID_DATA, "Invalid Data"));
            MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            System.out.println("-------------------------------" + mvcResult.getResponse().getStatus());
        } catch (BookStoreException e) {
            Assert.assertEquals(BookStoreException.ExceptionType.INVALID_DATA, e.type);
        }
    }
}