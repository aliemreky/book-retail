package com.getir.project.bookretail.response.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
public class OrderStatisticMonthly {

    private String month;

    private int totalOrderCount;

    private int totalBookCount;

    private double totalPurchasedAmount;

    public void setTotalPurchasedAmount(double totalPurchasedAmount) {
        BigDecimal bd = new BigDecimal(totalPurchasedAmount).setScale(2, RoundingMode.UP);
        this.totalPurchasedAmount = bd.doubleValue();
    }
}
