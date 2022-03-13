package com.getir.project.bookretail.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.getir.project.bookretail.util.constant.ErrorConstant.NOT_BLANK_FIELD;

@Data
public class BookRequest {

    private Long id;

    @NotBlank(message = "title" + NOT_BLANK_FIELD)
    private String title;

    private String description;

    @NotBlank(message = "publisher" + NOT_BLANK_FIELD)
    private String publisher;

    @NotNull(message = "publisherYear" + NOT_BLANK_FIELD)
    private Integer publisherYear;

    @NotBlank(message = "author" + NOT_BLANK_FIELD)
    private String author;

    @NotNull(message = "price" + NOT_BLANK_FIELD)
    private Double price;

    @NotNull(message = "stock" + NOT_BLANK_FIELD)
    private Integer stock;

    private String imageUrl;
}
