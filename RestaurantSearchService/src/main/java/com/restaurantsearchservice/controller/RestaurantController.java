package com.restaurantsearchservice.controller;

import com.restaurantsearchservice.dto.request.RestaurantRequest;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.entity.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/restaurants")
public interface RestaurantController {

    @GetMapping("/search/location")
    ResponseEntity<List<Restaurant>> findRestaurantByLocation(@RequestParam String location);
    @GetMapping("/search/distance")
    ResponseEntity<List<Restaurant>> findRestaurantByDistance(@RequestParam Double distance);
    @GetMapping("/search/cuisine")
    ResponseEntity<List<Restaurant>> findRestaurantByCuisine(@RequestParam String cuisine);
    @GetMapping("/search/budget")
    ResponseEntity<List<Restaurant>> findRestaurantByBudget(@RequestParam Double budget);
    @GetMapping("/search/name")
    ResponseEntity<List<Restaurant>> findRestaurantByName(@RequestParam String name);

    @GetMapping("/restaurant/{id}/menu")
    ResponseEntity<List<MenuItems>> findFoodMenuOfRestaurant(@PathVariable Long id);

    @GetMapping("/restaurant/{id}/restaurant-name")
    ResponseEntity<Optional<String>> getRestaurantName(@PathVariable Long id);

    @GetMapping("/")
    ResponseEntity<List<Restaurant>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Restaurant>> findById(@PathVariable Long id);

    @PostMapping("/restaurant")
    ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant);

    @PutMapping("/restaurant")
    ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteRestaurant(@PathVariable Long id);
}
