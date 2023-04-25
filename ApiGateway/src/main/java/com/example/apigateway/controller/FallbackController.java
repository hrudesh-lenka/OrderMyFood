package com.example.apigateway.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api")
public interface FallbackController {

    @RequestMapping("/order/order-fallback")
    @CircuitBreaker(name = "order-management-service")
    public Mono<String> orderManagentServiceFallBack();

    @RequestMapping("/restaurants/restaurant-search-fallback")
    @CircuitBreaker(name = "restaurant-search-service")
    public Mono<String> restaurantSearchServiceFallBack();
}
