package com.getir.project.bookretail.controller;

import com.getir.project.bookretail.request.BookRequest;
import com.getir.project.bookretail.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "${app.routes.root}")
public interface IBookController {

    @PostMapping(value = "${app.routes.book.create}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    BaseResponse createBook(@RequestBody @Valid BookRequest request);

    @PostMapping(value = "${app.routes.book.update}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    BaseResponse updateBook(@RequestBody @Valid BookRequest request);

}