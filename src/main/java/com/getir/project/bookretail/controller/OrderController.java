package com.getir.project.bookretail.controller;

import com.getir.project.bookretail.entity.Order;
import com.getir.project.bookretail.request.GetOrderDetailByIdRequest;
import com.getir.project.bookretail.request.GetOrderListByDateRequest;
import com.getir.project.bookretail.request.OrderRequest;
import com.getir.project.bookretail.request.StatisticMontlyRequest;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.response.GetOrderDetailResponse;
import com.getir.project.bookretail.response.bean.OrderStatisticMonthly;
import com.getir.project.bookretail.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController implements IOrderController {

    @Autowired
    private IOrderService orderService;

    @Override
    public BaseResponse createOrder(OrderRequest request) {
        return orderService.createOrder(request);
    }

    @Override
    public List<Order> getOrdersByDate(GetOrderListByDateRequest request){
        return orderService.getOrdersByDate(request);
    }

    @Override
    public List<OrderStatisticMonthly> getStatisticMonthly (StatisticMontlyRequest request){
        return orderService.getStatisticMonthly(request);
    }

    @Override
    public GetOrderDetailResponse getOrderDetailById(GetOrderDetailByIdRequest request){
        return orderService.getOrderDetailById(request);
    }

}
