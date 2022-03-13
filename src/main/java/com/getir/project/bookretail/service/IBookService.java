package com.getir.project.bookretail.service;

import com.getir.project.bookretail.request.BookRequest;
import com.getir.project.bookretail.response.BaseResponse;

public interface IBookService {

    BaseResponse createBook(BookRequest request);

    BaseResponse updateBook(BookRequest request);
}
