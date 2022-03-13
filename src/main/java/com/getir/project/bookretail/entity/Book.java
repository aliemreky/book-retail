package com.getir.project.bookretail.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    private long id;

    @Version
    private Long version;

    private String title;

    private String description;

    private String publisher;

    private Integer publisherYear;

    private String author;

    private Double price;

    private String imageUrl;

    private int stock;

    private LocalDateTime createdDate;
}
