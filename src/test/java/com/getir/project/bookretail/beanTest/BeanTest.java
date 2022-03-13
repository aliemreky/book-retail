package com.getir.project.bookretail.beanTest;

import com.getir.project.bookretail.entity.Book;
import com.getir.project.bookretail.entity.Customer;
import com.getir.project.bookretail.entity.DatabaseSequence;
import com.getir.project.bookretail.entity.Order;
import com.getir.project.bookretail.request.*;
import com.getir.project.bookretail.request.bean.BookOrder;
import com.getir.project.bookretail.response.BaseResponse;
import com.getir.project.bookretail.response.CustomerOrderResponse;
import com.getir.project.bookretail.response.CustomerSignUpResponse;
import com.getir.project.bookretail.response.GetOrderDetailResponse;
import com.getir.project.bookretail.response.bean.OrderStatisticMonthly;
import com.google.code.beanmatchers.BeanMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class BeanTest {

    @BeforeEach
    public void initMocks() {
        BeanMatchers.registerValueGenerator(() -> {
            long generatedLong = 1L + (long) (Math.random() * (1000L - 1L));
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(generatedLong), ZoneId.systemDefault());
        }, LocalDateTime.class);
        BeanMatchers.registerValueGenerator(() -> LocalDate.ofYearDay(2000, 1), LocalDate.class);
    }

    @Test
    public void testEntityBean() {
        assertThat(Book.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(Customer.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(DatabaseSequence.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(Order.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
    }

    @Test
    public void testRequestBean() {
        assertThat(BaseRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(BookRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(CustomerOrdersRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals()));
        assertThat(CustomerRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(GetOrderListByDateRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(OrderRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCodeExcluding("userEmail"), hasValidBeanEqualsExcluding("userEmail"), hasValidBeanToStringExcluding("userEmail")));
        assertThat(BookOrder.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(StatisticMontlyRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCodeExcluding("userEmail"), hasValidBeanEqualsExcluding("userEmail"), hasValidBeanToStringExcluding("userEmail")));
        assertThat(GetOrderDetailByIdRequest.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCodeExcluding("userEmail"), hasValidBeanEqualsExcluding("userEmail"), hasValidBeanToStringExcluding("userEmail")));
    }

    @Test
    public void testResponseBean() {
        assertThat(BaseResponse.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(CustomerOrderResponse.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToString()));
        assertThat(CustomerSignUpResponse.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanEquals()));
        assertThat(OrderStatisticMonthly.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSettersExcluding("totalPurchasedAmount"), hasValidBeanHashCode(), hasValidBeanEquals(), hasValidBeanToStringExcluding("totalPurchasedAmount")));
        assertThat(GetOrderDetailResponse.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSettersExcluding("order"), hasValidBeanHashCodeExcluding("error","order","message","status"), hasValidBeanEqualsExcluding("error","order","message","status"), hasValidBeanToStringExcluding("error","order","message","status")));
    }

}