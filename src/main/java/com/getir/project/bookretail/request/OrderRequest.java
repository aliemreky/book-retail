package com.getir.project.bookretail.request;

import com.getir.project.bookretail.request.bean.BookOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.getir.project.bookretail.util.constant.ErrorConstant.NOT_BLANK_FIELD;

@Data
public class OrderRequest extends BaseRequest {

    @NotNull(message = "orderList " + NOT_BLANK_FIELD)
    private List<BookOrder> orderList;
}
