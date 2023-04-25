package com.restaurantsearchservice.service;

import com.restaurantsearchservice.entity.MenuItems;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface MenuItemsService {

    List<MenuItems> getAllItems();

    Optional<MenuItems> getItemById(Long id);

    MenuItems saveItem(MenuItems menuItems);

    MenuItems updateItem(MenuItems menuItems);

    String deleteItem(Long id);
}
