package com.ordermanagementservice.dto.request;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long itemId;

    private String itemName;

    private Integer quantity;
}
