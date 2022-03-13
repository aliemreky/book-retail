package com.getir.project.bookretail.service;

import com.getir.project.bookretail.request.CustomerOrdersRequest;
import com.getir.project.bookretail.request.CustomerRequest;
import com.getir.project.bookretail.response.CustomerOrderResponse;
import com.getir.project.bookretail.response.CustomerSignUpResponse;

public interface IUserService {

    CustomerSignUpResponse signUp(CustomerRequest request);

    CustomerOrderResponse getCustomerOrders(CustomerOrdersRequest request);

}
