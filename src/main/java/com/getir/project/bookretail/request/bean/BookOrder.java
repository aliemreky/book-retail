package com.getir.project.bookretail.request.bean;

import lombok.Data;

@Data
public class BookOrder {

    private long bookId;

    private int orderCount;
}
