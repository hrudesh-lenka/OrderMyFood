package com.restaurantsearchservice.service;

import com.restaurantsearchservice.dto.request.RestaurantRequest;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<Restaurant> findRestaurantByLocation(String location);

    List<Restaurant> findRestaurantByDistance(Double distance);

    List<Restaurant> findRestaurantByCuisine(String cuisine);

    List<Restaurant> findRestaurantByBudget(Double budget);

    List<Restaurant> findRestaurantByName(String name);

    List<MenuItems> findFoodMenuOfRestaurant(Long id);

    Optional<String> getRestaurantName(Long id);

    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Restaurant restaurant);

    String deleteRestaurant(Long id);
}
