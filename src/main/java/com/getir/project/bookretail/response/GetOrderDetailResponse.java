package com.getir.project.bookretail.response;

import com.getir.project.bookretail.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetOrderDetailResponse extends BaseResponse{

    private Order order;
}
