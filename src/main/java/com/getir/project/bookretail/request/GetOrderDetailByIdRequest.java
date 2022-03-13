package com.getir.project.bookretail.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.getir.project.bookretail.util.constant.ErrorConstant.NOT_BLANK_FIELD;

@Data
@NoArgsConstructor
public class GetOrderDetailByIdRequest {

    @NotNull(message = "orderId " + NOT_BLANK_FIELD)
    private Long orderId;
}
