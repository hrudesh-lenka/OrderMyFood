package com.example.apigateway.controller;

import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackControllerImpl implements FallbackController{
    @Override
    public Mono<String> orderManagentServiceFallBack() {
        return Mono.just("Order Management Service is taking too long to respond or is down. Please try after sometime");
    }

    @Override
    public Mono<String> restaurantSearchServiceFallBack() {
        return Mono.just("Restaurant Search Service is taking too long to respond or is down. Please try after sometime");
    }
}
