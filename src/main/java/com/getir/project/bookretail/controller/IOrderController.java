package com.getir.project.bookretail.controller;

import com.getir.project.bookretail.entity.Order;
import com.getir.project.bookretail.request.GetOrderDetailByIdRequest;
import com.getir.project.bookretail.request.GetOrderListByDateRequest;
import com.getir.project.bookretail.request.OrderRequest;
import com.getir.project.bookretail.request.StatisticMontlyRequest;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.response.GetOrderDetailResponse;
import com.getir.project.bookretail.response.bean.OrderStatisticMonthly;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "${app.routes.root}")
public interface IOrderController {

    @PostMapping(value = "${app.routes.order.create}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    BaseResponse createOrder(@RequestBody @Valid OrderRequest request);

    @PostMapping(value = "${app.routes.order.getListByDate}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Order> getOrdersByDate(@RequestBody @Valid GetOrderListByDateRequest request);

    @PostMapping(value = "${app.routes.order.getStatisticMonthly}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<OrderStatisticMonthly> getStatisticMonthly (@RequestBody @Valid StatisticMontlyRequest request);

    @PostMapping(value = "${app.routes.order.detail}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    GetOrderDetailResponse getOrderDetailById(@RequestBody @Valid GetOrderDetailByIdRequest request);

}