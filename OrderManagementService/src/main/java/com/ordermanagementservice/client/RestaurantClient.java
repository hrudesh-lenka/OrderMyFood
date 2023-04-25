package com.ordermanagementservice.client;

import com.ordermanagementservice.dto.MenuItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "RESTAURANT-SEARCH-SERVICE")
public interface RestaurantClient {

    @GetMapping("/v1/api/menu-items/menu/{id}")
    ResponseEntity<Optional<MenuItem>> getItemById(@PathVariable Long id);

    @GetMapping("/v1/api/restaurants/restaurant/{id}/restaurant-name")
    Optional<String> getRestaurantNameById(@PathVariable Long id);
}
