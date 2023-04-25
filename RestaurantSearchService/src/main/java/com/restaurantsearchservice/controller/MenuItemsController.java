package com.restaurantsearchservice.controller;

import com.restaurantsearchservice.entity.MenuItems;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/menu-items")
public interface MenuItemsController {

    @GetMapping("/")
    ResponseEntity<List<MenuItems>> getAllItems();

    @GetMapping("/menu/{id}")
    ResponseEntity<Optional<MenuItems>> getItemById(@PathVariable Long id);

    @PostMapping("/menu")
    ResponseEntity<MenuItems> saveItem(@RequestBody MenuItems menuItems);

    @PutMapping("/menu")
    ResponseEntity<MenuItems> updateItem(@RequestBody MenuItems menuItems);

    @DeleteMapping("/menu/{id}")
    ResponseEntity<String> deleteItem(@PathVariable Long id);
}
