package com.restaurantsearchservice.dto.request;

import lombok.Data;

@Data
public class RestaurantRequest {
    private String location;
    private Double distance;
    private String cuisine;
    private Double budget;
    private String name;
}
