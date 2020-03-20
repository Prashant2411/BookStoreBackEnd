package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.model.OrderBookDetail;
import com.bridgelabz.bookstore.service.OrderBookDetailService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void init() {

    }
    OrderBookDetailDTO orderBookDetailDTO;

    @Test
    void givenRequest_WhenGetResponse_ItShouldReturnStatusOk() throws Exception {
        orderBookDetailDTO=new OrderBookDetailDTO("7 3", "2 3", 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk","vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail=new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(orderBookDetail);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200,status);
    }

    @Test
    void givenRequest_WhenGetResponse_ItsResponseShouldReturnCorrect() throws Exception {
        orderBookDetailDTO=new OrderBookDetailDTO("7 3", "2 3", 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk","vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail=new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(orderBookDetail);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("7 3"));
    }

    @Test
    void givenWrongUrl_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        orderBookDetailDTO=new OrderBookDetailDTO("7 3", "2 3", 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk","vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail=new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(orderBookDetail);
        MvcResult mvcResult = this.mockMvc.perform(post("/  bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404,status);
    }

    @Test
    void givenPutRequestInsteadOfPost_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        orderBookDetailDTO=new OrderBookDetailDTO("7 3", "2 3", 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk","vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail=new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(orderBookDetail);
        MvcResult mvcResult = this.mockMvc.perform(put("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(405,status);
    }

    @Test
    void givenAnotherContentType_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        orderBookDetailDTO=new OrderBookDetailDTO("7 3", "2 3", 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk","vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail=new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(orderBookDetail);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_ATOM_XML)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415,status);
    }

    @Test
    void givenRequestWithoutConvertItToJson_WhenGetResponse_ItsStatusShouldReturnBad() throws Exception {
        orderBookDetailDTO=new OrderBookDetailDTO("7 3","2 3", 6000.0, "jjggjmhk", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk","vghjnkml");
        OrderBookDetail orderBookDetail=new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenReturn(orderBookDetail);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(String.valueOf(orderBookDetail))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400,status);
    }

    @Test
    void givenRequestToController_WhenWrongRequestData_thenShouldThrowException() throws Exception {
        orderBookDetailDTO=new OrderBookDetailDTO("7 3", "2 3", 6000.0, "j", "9312345674", "400086", "tfjn", "fgbhjn tgyuhj", "cfgvhbj", "gvbhjnmk","vghjnkml");
        String jsonDto = gson.toJson(orderBookDetailDTO);
        OrderBookDetail orderBookDetail=new OrderBookDetail(orderBookDetailDTO);
        when(orderBookDetailService.addOrderBookSummary(any())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.INVALID_DATA, "Invalid Data"));;
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/orderbookdetail").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("Invalid Data", contentAsString);
    }




}

