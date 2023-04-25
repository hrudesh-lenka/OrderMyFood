package com.restaurantsearchservice.controller.impl;

import com.restaurantsearchservice.controller.RestaurantController;
import com.restaurantsearchservice.dto.request.RestaurantRequest;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.entity.Restaurant;
import com.restaurantsearchservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantControllerImpl implements RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public ResponseEntity<List<Restaurant>> findRestaurantByLocation(String location) {
        return new ResponseEntity<>(restaurantService.findRestaurantByLocation(location), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Restaurant>> findRestaurantByDistance(Double distance) {
        return new ResponseEntity<>(restaurantService.findRestaurantByDistance(distance), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Restaurant>> findRestaurantByCuisine(String cuisine) {
        return new ResponseEntity<>(restaurantService.findRestaurantByCuisine(cuisine),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Restaurant>> findRestaurantByBudget(Double budget) {
        return new ResponseEntity<>(restaurantService.findRestaurantByBudget(budget), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Restaurant>> findRestaurantByName(String name) {
        return new ResponseEntity<>(restaurantService.findRestaurantByName(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MenuItems>> findFoodMenuOfRestaurant(Long id) {
        return new ResponseEntity<>(restaurantService.findFoodMenuOfRestaurant(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<String>> getRestaurantName(Long id) {
        return new ResponseEntity<>(restaurantService.getRestaurantName(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Restaurant>> findAll() {
        return new ResponseEntity<>(restaurantService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<Restaurant>> findById(Long id) {
        return new ResponseEntity<>(restaurantService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Restaurant> addRestaurant(Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.addRestaurant(restaurant), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Restaurant> updateRestaurant(Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurant), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteRestaurant(Long id) {
        return new ResponseEntity<>(restaurantService.deleteRestaurant(id), HttpStatus.OK);
    }
}
