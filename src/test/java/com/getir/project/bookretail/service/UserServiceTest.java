package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Book;
import com.getir.project.bookretail.entity.Customer;
import com.getir.project.bookretail.repository.CustomerRepository;
import com.getir.project.bookretail.repository.OrderRepository;
import com.getir.project.bookretail.request.CustomerOrdersRequest;
import com.getir.project.bookretail.request.CustomerRequest;
import com.getir.project.bookretail.response.CustomerOrderResponse;
import com.getir.project.bookretail.response.CustomerSignUpResponse;
import com.getir.project.bookretail.util.ResponseMessage;
import com.getir.project.bookretail.util.enums.StatusEnum;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private CommonService commonService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void TestUserSignUpExists() {

        CustomerRequest request = new CustomerRequest();
        request.setEmail("test@test.com");

        Mockito.when(customerRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Customer()));

        CustomerSignUpResponse response = userService.signUp(request);

        Assertions.assertEquals(StatusEnum.FAILED.getValue(), response.getStatus());
        Assertions.assertEquals(ResponseMessage.CUSTOMER_ALREADY_EXISTS, response.getMessage());

    }

    @Test
    public void TestUserSignUpSuccess() {

        CustomerRequest request = new CustomerRequest();
        request.setEmail("TEST");
        request.setFullName("TEST");
        request.setPassword("TEST");

        Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("12321312312312");
        Mockito.when(customerRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        CustomerSignUpResponse response = userService.signUp(request);

        Assertions.assertEquals(StatusEnum.SUCCESS.getValue(), response.getStatus());
        Assertions.assertEquals(ResponseMessage.CUSTOMER_CREATED, response.getMessage());

    }

    @Test
    public void TestgetCustomerOrdersResponse() {

        CustomerOrdersRequest request = new CustomerOrdersRequest();
        request.setUserEmail("TEST");

        Customer customer = new Customer();
        customer.setEmail("TEST");
        customer.setId(10L);
        customer.setFullName("test");

        Mockito.when(commonService.getUserByEmail(request.getUserEmail())).thenReturn(customer);
        Mockito.when(orderRepository.findAllByIdIn(customer.getOrderList())).thenReturn(new ArrayList<>());

        CustomerOrderResponse customerOrders = userService.getCustomerOrders(request);

        Assertions.assertEquals(10L, customerOrders.getCustomerId());
        Assertions.assertEquals("test", customerOrders.getFullName());
        Assertions.assertEquals("TEST", customerOrders.getEmail());
        Assertions.assertEquals(new ArrayList<>(), customerOrders.getOrders());
    }

}