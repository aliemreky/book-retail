package com.getir.project.bookretail.controller;

import com.getir.project.bookretail.request.CustomerOrdersRequest;
import com.getir.project.bookretail.request.CustomerRequest;
import com.getir.project.bookretail.response.CustomerOrderResponse;
import com.getir.project.bookretail.response.CustomerSignUpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "${app.routes.root}")
public interface ICustomerController {

    @PostMapping(value = "${app.routes.customer.signUp}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    CustomerSignUpResponse createUserAction(@Valid @RequestBody CustomerRequest request);

    @PostMapping(value = "${app.routes.customer.orders}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    CustomerOrderResponse getCustomerOrders(@Valid @RequestBody CustomerOrdersRequest request);

}
