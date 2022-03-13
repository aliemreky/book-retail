package com.getir.project.bookretail.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private long id;

    private List<Book> bookList;

    private Customer customer;

    private int orderCount;

    private Double totalAmount;

    private LocalDateTime createdDate;
}
