package com.getir.project.bookretail.response;

import com.getir.project.bookretail.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerOrderResponse {

    private long customerId;

    private String fullName;

    private String email;

    private List<Order> orders;
}
