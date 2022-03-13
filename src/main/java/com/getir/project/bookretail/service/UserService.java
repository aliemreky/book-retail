package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Customer;
import com.getir.project.bookretail.repository.OrderRepository;
import com.getir.project.bookretail.repository.CustomerRepository;
import com.getir.project.bookretail.request.CustomerOrdersRequest;
import com.getir.project.bookretail.request.CustomerRequest;
import com.getir.project.bookretail.response.CustomerOrderResponse;
import com.getir.project.bookretail.response.CustomerSignUpResponse;
import com.getir.project.bookretail.util.ResponseMessage;
import com.getir.project.bookretail.util.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CommonService commonService;

    @Override
    public CustomerSignUpResponse signUp(CustomerRequest request) {
        CustomerSignUpResponse response = new CustomerSignUpResponse();

        Optional<Customer> userEntityExisting = customerRepository.findByEmail(request.getEmail());

        if (userEntityExisting.isPresent()) {
            response.setStatus(StatusEnum.FAILED.getValue());
            response.setMessage(ResponseMessage.CUSTOMER_ALREADY_EXISTS);
        } else {
            Customer newCustomer = new Customer();
            newCustomer.setId(commonService.generateSequence(Customer.SEQUENCE_NAME));
            newCustomer.setEmail(request.getEmail());
            newCustomer.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            newCustomer.setFullName(request.getFullName());

            customerRepository.save(newCustomer);

            response.setStatus(StatusEnum.SUCCESS.getValue());
            response.setMessage(ResponseMessage.CUSTOMER_CREATED);
        }
        return response;
    }

    @Override
    public CustomerOrderResponse getCustomerOrders(CustomerOrdersRequest request) {
        CustomerOrderResponse response = new CustomerOrderResponse();
        Customer customer = commonService.getUserByEmail(request.getUserEmail());
        response.setCustomerId(customer.getId());
        response.setEmail(customer.getEmail());
        response.setFullName(customer.getFullName());
        response.setOrders(orderRepository.findAllByIdIn(customer.getOrderList()));
        return response;
    }

}
