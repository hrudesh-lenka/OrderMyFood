package com.restaurantsearchservice.service.impl;

import com.restaurantsearchservice.advice.RestaurantException;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.entity.Restaurant;
import com.restaurantsearchservice.repository.RestaurantRepository;
import com.restaurantsearchservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findRestaurantByLocation(String location) {
        Optional<List<Restaurant>> res = restaurantRepository.findRestaurantByLocation(location);
        if(!res.isPresent()){
            throw new RestaurantException("No Restaurant found near this location "+location);
        }
        return res.get();
    }

    @Override
    public List<Restaurant> findRestaurantByDistance(Double distance) {
        Optional<List<Restaurant>> res = restaurantRepository.findRestaurantByDistance(distance);
        if(!res.isPresent()){
            throw new RestaurantException("No Restaurant found within this range "+distance);
        }
        return res.get();
    }

    @Override
    public List<Restaurant> findRestaurantByCuisine(String cuisine) {
        Optional<List<Restaurant>> res =  restaurantRepository.findRestaurantByCuisine(cuisine);
        if(!res.isPresent()){
            throw new RestaurantException("No Restaurant found with this cuisine "+cuisine);
        }
        return res.get();
    }

    @Override
    public List<Restaurant> findRestaurantByBudget(Double budget) {
        Optional<List<Restaurant>> res= restaurantRepository.findRestaurantByBudget(budget);
        if(!res.isPresent()){
            throw new RestaurantException("No Restaurant found within this budget "+budget);
        }
        return res.get();
    }

    @Override
    public List<Restaurant> findRestaurantByName(String name) {
        Optional<List<Restaurant>> res=  restaurantRepository.findRestaurantByName(name);
        if(!res.isPresent()){
            throw new RestaurantException("No Restaurant found by this name "+name);
        }
        return res.get();
    }

    @Override
    public List<MenuItems> findFoodMenuOfRestaurant(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (!restaurant.isPresent()){
                throw new RestaurantException("Restaurant not found");
        }
        List<MenuItems> menuItems = restaurant.get().getMenuItems();
        return menuItems;
    }

    @Override
    public Optional<String> getRestaurantName(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (!restaurant.isPresent()){
            throw new RestaurantException("Restaurant not found");
        }
        return Optional.ofNullable(restaurant.get().getName());
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Optional<Restaurant> optRes = restaurantRepository.findById(restaurant.getId());
        if (!optRes.isPresent()){
            throw new RestaurantException("Restaurant not found");
        }
        Restaurant res = optRes.get();
        res.setBudget(restaurant.getBudget());
        res.setDistance(restaurant.getDistance());
        res.setCuisine(restaurant.getCuisine());
        res.setName(restaurant.getName());
        res.setMenuItems(restaurant.getMenuItems());
        res.setLocation(restaurant.getLocation());
        return restaurantRepository.save(res);
    }

    @Override
    public String deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
        return "Restaurant has been deleted successfully";
    }
}
