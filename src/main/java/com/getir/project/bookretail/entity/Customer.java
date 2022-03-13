package com.getir.project.bookretail.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "user")
public class Customer implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    private String fullName;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private List<Long> orderList;

    private LocalDateTime createDate;

    public Customer(){
        this.setCreateDate(LocalDateTime.now());
        this.orderList = new ArrayList<>();
    }
}
