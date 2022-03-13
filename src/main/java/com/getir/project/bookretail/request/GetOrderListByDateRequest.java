package com.getir.project.bookretail.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.getir.project.bookretail.util.constant.ErrorConstant.NOT_BLANK_FIELD;

@Data
public class GetOrderListByDateRequest {

    @NotBlank(message = "startDate" + NOT_BLANK_FIELD)
    private String startDate;

    @NotBlank(message = "endDate" + NOT_BLANK_FIELD)
    private String endDate;
}
