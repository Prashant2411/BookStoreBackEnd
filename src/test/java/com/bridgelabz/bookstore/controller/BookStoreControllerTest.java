package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.Exception.BookStoreException;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.service.BookService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class BookStoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    Gson gson = new Gson();
    BookDTO bookDTO;

    @Test
    void givenRequest_WhenGetResponse_ItShouldReturnStatusOk() throws Exception {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        String jsonDto = gson.toJson(bookDTO);
        BookDetails bookDetails = new BookDetails(bookDTO);
        when(bookService.addBook(any())).thenReturn(bookDetails);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/addbook").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

    }

    @Test
    void givenRequest_WhenGetResponse_ItsResponseShouldReturnCorrect() throws Exception {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        String jsonDto = gson.toJson(bookDTO);
        BookDetails bookDetails = new BookDetails(bookDTO);
        when(bookService.addBook(any())).thenReturn(bookDetails);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/addbook").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("steve"));
    }

    @Test
    void givenWrongUrl_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2014);
        String jsonDto = gson.toJson(bookDTO);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstoreaddbook").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }

    @Test
    void givenGetRequestInsteadOfPost_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        String jsonDto = gson.toJson(bookDTO);
        MvcResult mvcResult = this.mockMvc.perform(get("/bookstore/addbook").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(405, status);
    }

    @Test
    void givenAnotherContentType_WhenGetResponse_ItShouldReturnStatusBad() throws Exception {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        String jsonDto = gson.toJson(bookDTO);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/addbook").content(jsonDto)
                .contentType(MediaType.APPLICATION_ATOM_XML_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(415, status);
    }

    @Test
    void givenRequestWithoutConvertItToJson_WhenGetResponse_ItsStatusShouldReturnBad() throws Exception {
        bookDTO = new BookDTO("make me think", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 2013);
        MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/addbook").content(String.valueOf(bookDTO))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    void givenRequestToController_WhenWrongRequestData_thenShouldThrowException() throws Exception {
            bookDTO = new BookDTO("m", "steve", 1500.0, 5, "sdrftgvhbjnkm", "sedcfgvbh", 900);
            String json = gson.toJson(bookDTO);
            when(bookService.addBook(any())).thenThrow(new BookStoreException(BookStoreException.ExceptionType.INVALID_DATA, "Invalid Data"));
            MvcResult mvcResult = this.mockMvc.perform(post("/bookstore/addbook").content(json)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("Invalid Data",contentAsString);
    }
}
