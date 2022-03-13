package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Order;
import com.getir.project.bookretail.repository.BookRepository;
import com.getir.project.bookretail.repository.CustomerRepository;
import com.getir.project.bookretail.repository.OrderRepository;
import com.getir.project.bookretail.request.StatisticMontlyRequest;
import com.getir.project.bookretail.response.bean.OrderStatisticMonthly;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private CommonService commonService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void getStatisticMonthly_Success() {
        StatisticMontlyRequest request = new StatisticMontlyRequest();

        List<Order> orderList = new ArrayList<>();

        Order order = new Order();
        order.setCreatedDate(LocalDateTime.now());
        order.setOrderCount(10);
        order.setTotalAmount(Double.valueOf("100"));
        Order order2 = new Order();
        order2.setCreatedDate(LocalDateTime.now());
        order2.setOrderCount(3);
        order2.setTotalAmount(Double.valueOf("24"));
        Order order1 = new Order();
        order1.setCreatedDate(LocalDateTime.of(2022, 1, 15, 23, 20));
        order1.setOrderCount(2);
        order1.setTotalAmount(Double.valueOf("75"));

        orderList.add(order);
        orderList.add(order1);
        orderList.add(order2);

        Mockito.when(orderRepository.findAllByCustomer(request.getUserEmail())).thenReturn(orderList);

        List<OrderStatisticMonthly> monthlyList = orderService.getStatisticMonthly(request);
        Assertions.assertEquals(2, monthlyList.size());
    }

}