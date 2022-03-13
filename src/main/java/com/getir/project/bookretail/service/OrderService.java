package com.getir.project.bookretail.service;

import com.getir.project.bookretail.entity.Book;
import com.getir.project.bookretail.entity.Customer;
import com.getir.project.bookretail.entity.Order;
import com.getir.project.bookretail.repository.BookRepository;
import com.getir.project.bookretail.repository.CustomerRepository;
import com.getir.project.bookretail.repository.OrderRepository;
import com.getir.project.bookretail.request.GetOrderDetailByIdRequest;
import com.getir.project.bookretail.request.GetOrderListByDateRequest;
import com.getir.project.bookretail.request.OrderRequest;
import com.getir.project.bookretail.request.StatisticMontlyRequest;
import com.getir.project.bookretail.request.bean.BookOrder;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.response.GetOrderDetailResponse;
import com.getir.project.bookretail.response.bean.OrderStatisticMonthly;
import com.getir.project.bookretail.util.ResponseMessage;
import com.getir.project.bookretail.util.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public BaseResponse createOrder(OrderRequest request) {
        BaseResponse response = new BaseResponse();
        List<String> error = new ArrayList<>();
        List<Book> successOrdered = new ArrayList<>();
        int orderCount = 0;
        double totalAmount = 0;

        Customer customer = commonService.getUserByEmail(request.getUserEmail());

        for (BookOrder ord : request.getOrderList()) {
            Book book = bookRepository.findById(ord.getBookId()).get();
            if (book.getStock() < ord.getOrderCount()) {
                error.add(book.getTitle() + " (#id - " + book.getId() + ")" + " : Order Count is bigger than book stock count !");
            } else {
                orderCount += ord.getOrderCount();
                totalAmount += (book.getPrice() * ord.getOrderCount());
                book.setStock(book.getStock() - ord.getOrderCount());
                successOrdered.add(book);
            }
        }

        if (!successOrdered.isEmpty()) {
            Order newOrder = new Order();
            newOrder.setId(commonService.generateSequence(Order.SEQUENCE_NAME));
            newOrder.setCustomer(customer);
            newOrder.setBookList(successOrdered);
            newOrder.setCreatedDate(LocalDateTime.now());
            newOrder.setOrderCount(orderCount);
            newOrder.setTotalAmount(totalAmount);

            orderRepository.save(newOrder);
            bookRepository.saveAll(successOrdered);

            customer.getOrderList().add(newOrder.getId());
            customerRepository.save(customer);

            response.setStatus(StatusEnum.SUCCESS.getValue());
            response.setMessage((!error.isEmpty()) ? ResponseMessage.ORDER_CREATED_WITH_WARNING : ResponseMessage.ORDER_CREATED);
            response.setError(error);
        } else {
            response.setStatus(StatusEnum.FAILED.getValue());
            response.setMessage(ResponseMessage.NO_STOCK_BOOK_IN_REQUEST);
        }

        return response;
    }

    @Override
    public GetOrderDetailResponse getOrderDetailById(GetOrderDetailByIdRequest request){

        GetOrderDetailResponse response = new GetOrderDetailResponse();

        if (request.getOrderId() == null) {
            response.setStatus(StatusEnum.FAILED.getValue());
            response.setMessage(ResponseMessage.ORDER_ID_NOT_BE_EMPTY);
            return response;
        }

        Optional<Order> order = orderRepository.findById(request.getOrderId());
        order.ifPresent(response::setOrder);

        response.setStatus(StatusEnum.SUCCESS.getValue());
        response.setMessage(ResponseMessage.SUCCESS);

        return response;

    }

    @Override
    public List<Order> getOrdersByDate(GetOrderListByDateRequest request) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDateTime startDate = LocalDate.parse(request.getStartDate(), dtf).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(request.getEndDate(), dtf).atStartOfDay();

        return orderRepository.findAllByCreatedDateBetween(startDate, endDate);
    }

    @Override
    public List<OrderStatisticMonthly> getStatisticMonthly(StatisticMontlyRequest request) {
        List<Order> orderList = orderRepository.findAllByCustomer(request.getUserEmail());
        Map<String, OrderStatisticMonthly> monthly = new HashMap<>();

        orderList.forEach(k -> {
            String monthName = k.getCreatedDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            if (monthly.containsKey(monthName)) {
                OrderStatisticMonthly statisticMonthly = monthly.get(monthName);
                statisticMonthly.setTotalOrderCount(statisticMonthly.getTotalBookCount() + 1);
                statisticMonthly.setTotalBookCount(statisticMonthly.getTotalBookCount() + k.getOrderCount());
                statisticMonthly.setTotalPurchasedAmount(statisticMonthly.getTotalPurchasedAmount() + k.getTotalAmount());
                monthly.put(monthName, statisticMonthly);
            } else {
                OrderStatisticMonthly statisticMonthly = new OrderStatisticMonthly();
                statisticMonthly.setMonth(monthName);
                statisticMonthly.setTotalOrderCount(1);
                statisticMonthly.setTotalBookCount(k.getOrderCount());
                statisticMonthly.setTotalPurchasedAmount(k.getTotalAmount());
                monthly.put(monthName, statisticMonthly);
            }
        });

        return new ArrayList<>(monthly.values());
    }
}
