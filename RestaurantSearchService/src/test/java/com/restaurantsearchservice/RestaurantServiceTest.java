package com.restaurantsearchservice;

import com.restaurantsearchservice.advice.RestaurantException;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.entity.Restaurant;
import com.restaurantsearchservice.repository.RestaurantRepository;
import com.restaurantsearchservice.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RestaurantServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class RestaurantServiceTest {

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantServiceImpl restaurantServiceImpl;

    @Test
    void testFindRestaurantByLocation() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepository.findRestaurantByLocation(Mockito.<String>any()))
                .thenReturn(Optional.of(restaurantList));
        List<Restaurant> actualFindRestaurantByLocationResult = restaurantServiceImpl
                .findRestaurantByLocation("Bhubaneswar");
        assertSame(restaurantList, actualFindRestaurantByLocationResult);
        assertTrue(actualFindRestaurantByLocationResult.isEmpty());
        verify(restaurantRepository).findRestaurantByLocation(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByLocation2() {
        when(restaurantRepository.findRestaurantByLocation(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByLocation("Bhubaneswar"));
        verify(restaurantRepository).findRestaurantByLocation(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByLocation3() {
        when(restaurantRepository.findRestaurantByLocation(Mockito.<String>any()))
                .thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByLocation("Bhubaneswar"));
        verify(restaurantRepository).findRestaurantByLocation(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByDistance() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepository.findRestaurantByDistance(Mockito.<Double>any()))
                .thenReturn(Optional.of(restaurantList));
        List<Restaurant> actualFindRestaurantByDistanceResult = restaurantServiceImpl.findRestaurantByDistance(100d);
        assertSame(restaurantList, actualFindRestaurantByDistanceResult);
        assertTrue(actualFindRestaurantByDistanceResult.isEmpty());
        verify(restaurantRepository).findRestaurantByDistance(Mockito.<Double>any());
    }


    @Test
    void testFindRestaurantByDistance2() {
        when(restaurantRepository.findRestaurantByDistance(Mockito.<Double>any())).thenReturn(Optional.empty());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByDistance(100d));
        verify(restaurantRepository).findRestaurantByDistance(Mockito.<Double>any());
    }


    @Test
    void testFindRestaurantByDistance3() {
        when(restaurantRepository.findRestaurantByDistance(Mockito.<Double>any()))
                .thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByDistance(100d));
        verify(restaurantRepository).findRestaurantByDistance(Mockito.<Double>any());
    }


    @Test
    void testFindRestaurantByCuisine() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepository.findRestaurantByCuisine(Mockito.<String>any())).thenReturn(Optional.of(restaurantList));
        List<Restaurant> actualFindRestaurantByCuisineResult = restaurantServiceImpl.findRestaurantByCuisine("Indian");
        assertSame(restaurantList, actualFindRestaurantByCuisineResult);
        assertTrue(actualFindRestaurantByCuisineResult.isEmpty());
        verify(restaurantRepository).findRestaurantByCuisine(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByCuisine2() {
        when(restaurantRepository.findRestaurantByCuisine(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByCuisine("Indian"));
        verify(restaurantRepository).findRestaurantByCuisine(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByCuisine3() {
        when(restaurantRepository.findRestaurantByCuisine(Mockito.<String>any()))
                .thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByCuisine("Indian"));
        verify(restaurantRepository).findRestaurantByCuisine(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByBudget() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepository.findRestaurantByBudget(Mockito.<Double>any())).thenReturn(Optional.of(restaurantList));
        List<Restaurant> actualFindRestaurantByBudgetResult = restaurantServiceImpl.findRestaurantByBudget(100d);
        assertSame(restaurantList, actualFindRestaurantByBudgetResult);
        assertTrue(actualFindRestaurantByBudgetResult.isEmpty());
        verify(restaurantRepository).findRestaurantByBudget(Mockito.<Double>any());
    }


    @Test
    void testFindRestaurantByBudget2() {
        when(restaurantRepository.findRestaurantByBudget(Mockito.<Double>any())).thenReturn(Optional.empty());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByBudget(100d));
        verify(restaurantRepository).findRestaurantByBudget(Mockito.<Double>any());
    }


    @Test
    void testFindRestaurantByBudget3() {
        when(restaurantRepository.findRestaurantByBudget(Mockito.<Double>any()))
                .thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByBudget(100d));
        verify(restaurantRepository).findRestaurantByBudget(Mockito.<Double>any());
    }


    @Test
    void testFindRestaurantByName() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepository.findRestaurantByName(Mockito.<String>any())).thenReturn(Optional.of(restaurantList));
        List<Restaurant> actualFindRestaurantByNameResult = restaurantServiceImpl.findRestaurantByName("Mughlai");
        assertSame(restaurantList, actualFindRestaurantByNameResult);
        assertTrue(actualFindRestaurantByNameResult.isEmpty());
        verify(restaurantRepository).findRestaurantByName(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByName2() {
        when(restaurantRepository.findRestaurantByName(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByName("Mughali"));
        verify(restaurantRepository).findRestaurantByName(Mockito.<String>any());
    }


    @Test
    void testFindRestaurantByName3() {
        when(restaurantRepository.findRestaurantByName(Mockito.<String>any()))
                .thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findRestaurantByName("Mughlai"));
        verify(restaurantRepository).findRestaurantByName(Mockito.<String>any());
    }


    @Test
    void testFindFoodMenuOfRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setBudget(100d);
        restaurant.setCuisine("Cuisine");
        restaurant.setDistance(100d);
        restaurant.setId(1L);
        restaurant.setLocation("Bhubaneswar");
        ArrayList<MenuItems> menuItems = new ArrayList<>();
        restaurant.setMenuItems(menuItems);
        restaurant.setName("Mughlai");
        Optional<Restaurant> ofResult = Optional.of(restaurant);
        when(restaurantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<MenuItems> actualFindFoodMenuOfRestaurantResult = restaurantServiceImpl.findFoodMenuOfRestaurant(1L);
        assertSame(menuItems, actualFindFoodMenuOfRestaurantResult);
        assertTrue(actualFindFoodMenuOfRestaurantResult.isEmpty());
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testFindFoodMenuOfRestaurant2() {
        when(restaurantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findFoodMenuOfRestaurant(1L));
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testFindFoodMenuOfRestaurant3() {
        when(restaurantRepository.findById(Mockito.<Long>any())).thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findFoodMenuOfRestaurant(1L));
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testGetRestaurantName() {
        Restaurant restaurant = new Restaurant();
        restaurant.setBudget(100d);
        restaurant.setCuisine("Indian");
        restaurant.setDistance(100d);
        restaurant.setId(1L);
        restaurant.setLocation("Bhubaneswar");
        restaurant.setMenuItems(new ArrayList<>());
        restaurant.setName("Mughlai");
        Optional<Restaurant> ofResult = Optional.of(restaurant);
        when(restaurantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<String> actualRestaurantName = restaurantServiceImpl.getRestaurantName(1L);
        assertTrue(actualRestaurantName.isPresent());
        assertEquals("Mughlai", actualRestaurantName.get());
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testGetRestaurantName2() {
        when(restaurantRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.getRestaurantName(1L));
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testGetRestaurantName3() {
        when(restaurantRepository.findById(Mockito.<Long>any())).thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.getRestaurantName(1L));
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testFindAll() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepository.findAll()).thenReturn(restaurantList);
        List<Restaurant> actualFindAllResult = restaurantServiceImpl.findAll();
        assertSame(restaurantList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(restaurantRepository).findAll();
    }


    @Test
    void testFindAll2() {
        when(restaurantRepository.findAll()).thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findAll());
        verify(restaurantRepository).findAll();
    }


    @Test
    void testFindById() {
        Restaurant restaurant = new Restaurant();
        restaurant.setBudget(100d);
        restaurant.setCuisine("Indian");
        restaurant.setDistance(100d);
        restaurant.setId(1L);
        restaurant.setLocation("Bhubaneswar");
        restaurant.setMenuItems(new ArrayList<>());
        restaurant.setName("Mughlai");
        Optional<Restaurant> ofResult = Optional.of(restaurant);
        when(restaurantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Restaurant> actualFindByIdResult = restaurantServiceImpl.findById(1L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testFindById2() {
        when(restaurantRepository.findById(Mockito.<Long>any())).thenThrow(new RestaurantException("An error occurred"));
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.findById(1L));
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testAddRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setBudget(100d);
        restaurant.setCuisine("Indian");
        restaurant.setDistance(100d);
        restaurant.setId(1L);
        restaurant.setLocation("Bhubaneswar");
        restaurant.setMenuItems(new ArrayList<>());
        restaurant.setName("Mughlai");
        when(restaurantRepository.save(Mockito.<Restaurant>any())).thenReturn(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setBudget(100d);
        restaurant2.setCuisine("chinese");
        restaurant2.setDistance(100d);
        restaurant2.setId(2L);
        restaurant2.setLocation("Patia");
        restaurant2.setMenuItems(new ArrayList<>());
        restaurant2.setName("Regal");
        assertSame(restaurant, restaurantServiceImpl.addRestaurant(restaurant2));
        verify(restaurantRepository).save(Mockito.<Restaurant>any());
    }


    @Test
    void testAddRestaurant2() {
        when(restaurantRepository.save(Mockito.<Restaurant>any()))
                .thenThrow(new RestaurantException("An error occurred"));

        Restaurant restaurant = new Restaurant();
        restaurant.setBudget(100d);
        restaurant.setCuisine("Indian");
        restaurant.setDistance(100d);
        restaurant.setId(1L);
        restaurant.setLocation("Bhubaneswar");
        restaurant.setMenuItems(new ArrayList<>());
        restaurant.setName("Mughlai");
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.addRestaurant(restaurant));
        verify(restaurantRepository).save(Mockito.<Restaurant>any());
    }


    @Test
    void testUpdateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setBudget(100d);
        restaurant.setCuisine("Indian");
        restaurant.setDistance(100d);
        restaurant.setId(1L);
        restaurant.setLocation("Bhubaneswar");
        restaurant.setMenuItems(new ArrayList<>());
        restaurant.setName("Mughlai");
        Optional<Restaurant> ofResult = Optional.of(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setBudget(100d);
        restaurant2.setCuisine("chinese");
        restaurant2.setDistance(100d);
        restaurant2.setId(2L);
        restaurant2.setLocation("Patia");
        restaurant2.setMenuItems(new ArrayList<>());
        restaurant2.setName("Regal");
        when(restaurantRepository.save(Mockito.<Restaurant>any())).thenReturn(restaurant2);
        when(restaurantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setBudget(100d);
        restaurant3.setCuisine("chinese");
        restaurant3.setDistance(100d);
        restaurant3.setId(3L);
        restaurant3.setLocation("Patia");
        restaurant3.setMenuItems(new ArrayList<>());
        restaurant3.setName("New Mughlai");
        assertSame(restaurant2, restaurantServiceImpl.updateRestaurant(restaurant3));
        verify(restaurantRepository).save(Mockito.<Restaurant>any());
        verify(restaurantRepository).findById(Mockito.<Long>any());
    }




    @Test
    void testDeleteRestaurant() {
        doNothing().when(restaurantRepository).deleteById(Mockito.<Long>any());
        assertEquals("Restaurant has been deleted successfully", restaurantServiceImpl.deleteRestaurant(1L));
        verify(restaurantRepository).deleteById(Mockito.<Long>any());
    }


    @Test
    void testDeleteRestaurant2() {
        doThrow(new RestaurantException("An error occurred")).when(restaurantRepository).deleteById(Mockito.<Long>any());
        assertThrows(RestaurantException.class, () -> restaurantServiceImpl.deleteRestaurant(1L));
        verify(restaurantRepository).deleteById(Mockito.<Long>any());
    }
}
