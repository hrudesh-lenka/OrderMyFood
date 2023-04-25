package com.ordermanagementservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItem {
    private Long id;

    private String itemName;

    private BigDecimal price;

    private Long restaurantId;
}
