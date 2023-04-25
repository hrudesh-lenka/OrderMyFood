package com.ordermanagementservice.dto.request;

import lombok.Data;

@Data
public class UpdateOrderRequest extends OrderRequest{

    private Long orderId;
}
