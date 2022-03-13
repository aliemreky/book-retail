package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Book;
import com.getir.project.bookretail.repository.BookRepository;
import com.getir.project.bookretail.request.BookRequest;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.util.ResponseMessage;
import com.getir.project.bookretail.util.enums.StatusEnum;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Mock
    private CommonService commonService;

    @Test
    public void createBookService_AlReadyExist() {
        List<Book> bookList = new ArrayList<>();

        bookList.add(new Book());
        bookList.add(new Book());

        BookRequest request = new BookRequest();
        request.setAuthor("TEST");
        request.setDescription("TEST");
        request.setImageUrl("TEST");
        request.setPublisher("TEST");
        request.setStock(5);
        request.setPublisherYear(2015);
        request.setPrice(50.45);
        request.setTitle("TEST Test");

        Mockito.when(bookRepository.getBooksByTitleAndAuthor(request.getTitle(), request.getAuthor())).thenReturn(bookList);
        Assertions.assertEquals(StatusEnum.FAILED.getValue(), bookService.createBook(request).getStatus());
        Assertions.assertEquals(ResponseMessage.BOOK_ALREADY_EXISTS, bookService.createBook(request).getMessage());
    }

    @Test
    public void createBookService_Success() {
        List<Book> bookList = new ArrayList<>();

        BookRequest request = new BookRequest();
        request.setAuthor("TEST");
        request.setDescription("TEST");
        request.setImageUrl("TEST");
        request.setPublisher("TEST");
        request.setStock(5);
        request.setPublisherYear(2015);
        request.setPrice(50.45);
        request.setTitle("TEST Test");

        Mockito.when(commonService.generateSequence(Book.SEQUENCE_NAME)).thenReturn(Long.valueOf("111"));
        Mockito.when(bookRepository.getBooksByTitleAndAuthor(request.getTitle(), request.getAuthor())).thenReturn(bookList);
        Assertions.assertEquals(StatusEnum.SUCCESS.getValue(), bookService.createBook(request).getStatus());
        Assertions.assertEquals(ResponseMessage.BOOK_CREATED, bookService.createBook(request).getMessage());
    }

    @Test
    public void updateBookService_Success() {
        BookRequest request = new BookRequest();
        request.setId(10L);
        request.setAuthor("TEST");
        request.setDescription("TEST");
        request.setImageUrl("TEST");
        request.setPublisher("TEST");
        request.setStock(5);
        request.setPublisherYear(2015);
        request.setPrice(50.45);
        request.setTitle("TEST Test");

        Book book = new Book();
        book.setStock(15);
        book.setTitle("Brown");

        Mockito.when(bookRepository.findById(request.getId())).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        BaseResponse response = bookService.updateBook(request);
        Assertions.assertEquals(StatusEnum.SUCCESS.getValue(), response.getStatus());
        Assertions.assertEquals(ResponseMessage.BOOK_UPDATED, response.getMessage());
    }

    @Test
    public void updateBookService_ID_NULL_Error() {
        BookRequest request = new BookRequest();

        BaseResponse response = bookService.updateBook(request);
        Assertions.assertEquals(StatusEnum.FAILED.getValue(), response.getStatus());
        Assertions.assertEquals(ResponseMessage.ID_NOT_NULL, response.getMessage());
    }

    @Test
    public void updateBookService_BOOK_NOT_FOUND() {
        BookRequest request = new BookRequest();
        request.setId(10L);
        request.setAuthor("TEST");
        request.setDescription("TEST");
        request.setImageUrl("TEST");
        request.setPublisher("TEST");
        request.setStock(5);
        request.setPublisherYear(2015);
        request.setPrice(50.45);
        request.setTitle("TEST Test");

        Book book = new Book();
        book.setStock(15);
        book.setTitle("Brown");

        Mockito.when(bookRepository.findById(request.getId())).thenReturn(Optional.empty());

        BaseResponse response = bookService.updateBook(request);
        Assertions.assertEquals(StatusEnum.FAILED.getValue(), response.getStatus());
        Assertions.assertEquals(ResponseMessage.BOOK_NOT_FOUND, response.getMessage());
    }

}