package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Order;
import com.getir.project.bookretail.request.GetOrderDetailByIdRequest;
import com.getir.project.bookretail.request.GetOrderListByDateRequest;
import com.getir.project.bookretail.request.OrderRequest;
import com.getir.project.bookretail.request.StatisticMontlyRequest;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.response.GetOrderDetailResponse;
import com.getir.project.bookretail.response.bean.OrderStatisticMonthly;

import java.util.List;

public interface IOrderService {

    BaseResponse createOrder(OrderRequest request);

    List<Order> getOrdersByDate(GetOrderListByDateRequest request);

    List<OrderStatisticMonthly> getStatisticMonthly (StatisticMontlyRequest request);

    GetOrderDetailResponse getOrderDetailById(GetOrderDetailByIdRequest request);

}
