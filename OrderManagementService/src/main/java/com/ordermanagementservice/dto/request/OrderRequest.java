package com.ordermanagementservice.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {

    private Long userId;

    private Long restaurantId;

    private List<OrderItemRequest> orderItemRequests;
}
