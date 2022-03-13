package com.getir.project.bookretail.controller;

import com.getir.project.bookretail.request.CustomerOrdersRequest;
import com.getir.project.bookretail.request.CustomerRequest;
import com.getir.project.bookretail.response.CustomerOrderResponse;
import com.getir.project.bookretail.response.CustomerSignUpResponse;
import com.getir.project.bookretail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements ICustomerController {

    @Autowired
    private IUserService userService;

    @Override
    public CustomerSignUpResponse createUserAction(CustomerRequest request) {
        return userService.signUp(request);
    }

    @Override
    public CustomerOrderResponse getCustomerOrders(CustomerOrdersRequest request){
        return userService.getCustomerOrders(request);
    }
}
