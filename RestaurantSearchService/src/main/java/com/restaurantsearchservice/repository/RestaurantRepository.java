package com.restaurantsearchservice.repository;

import com.restaurantsearchservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select e from Restaurant e where e.location = :location")
    Optional<List<Restaurant>> findRestaurantByLocation(@Param("location") String location);

    @Query("select e from Restaurant e where e.distance <= :distance")
    Optional<List<Restaurant>> findRestaurantByDistance(@Param("distance") Double distance);

    @Query("select e from Restaurant e where e.cuisine = :cuisine")
    Optional<List<Restaurant>> findRestaurantByCuisine(@Param("cuisine") String cuisine);

    @Query("select e from Restaurant e where e.budget <= :budget")
    Optional<List<Restaurant>> findRestaurantByBudget(@Param("budget") Double budget);

    @Query("select e from Restaurant e where e.name = :name")
    Optional<List<Restaurant>> findRestaurantByName(@Param("name") String name);
}
