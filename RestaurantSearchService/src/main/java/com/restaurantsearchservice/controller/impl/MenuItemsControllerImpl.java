package com.restaurantsearchservice.controller.impl;


import com.restaurantsearchservice.controller.MenuItemsController;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MenuItemsControllerImpl implements MenuItemsController {

    @Autowired
    private MenuItemsService menuItemsService;
    @Override
    public ResponseEntity<List<MenuItems>> getAllItems() {
        return new ResponseEntity<>(menuItemsService.getAllItems(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<MenuItems>> getItemById(Long id) {
        return new ResponseEntity<>(menuItemsService.getItemById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MenuItems> saveItem(MenuItems menuItems) {
        return new ResponseEntity<>(menuItemsService.saveItem(menuItems), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MenuItems> updateItem(MenuItems menuItems) {
        return new ResponseEntity<>(menuItemsService.updateItem(menuItems), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteItem(Long id) {
        return new ResponseEntity<>(menuItemsService.deleteItem(id), HttpStatus.OK);
    }
}
