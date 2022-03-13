package com.getir.project.bookretail.controller;

import com.getir.project.bookretail.request.BookRequest;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController implements IBookController {

    @Autowired
    private IBookService bookService;

    @Override
    public BaseResponse createBook(BookRequest request) {
        return bookService.createBook(request);
    }

    @Override
    public BaseResponse updateBook(BookRequest request) {
        return bookService.updateBook(request);
    }

}
