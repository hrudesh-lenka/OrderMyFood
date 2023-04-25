package com.restaurantsearchservice.service.impl;

import com.restaurantsearchservice.advice.MenuException;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.repository.MenuItemsRepository;
import com.restaurantsearchservice.service.MenuItemsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MenuItemsServiceImpl implements MenuItemsService {

    private final MenuItemsRepository menuItemsRepository;

    public MenuItemsServiceImpl(MenuItemsRepository menuItemsRepository) {
        this.menuItemsRepository = menuItemsRepository;
    }

    @Override
    public List<MenuItems> getAllItems() {
        return menuItemsRepository.findAll();
    }

    @Override
    public Optional<MenuItems> getItemById(Long id) {
        return menuItemsRepository.findById(id);
    }

    @Override
    public MenuItems saveItem(MenuItems menuItems) {
        return menuItemsRepository.save(menuItems);
    }

    @Override
    public MenuItems updateItem(MenuItems menuItems) {
        Optional<MenuItems> optMenu = menuItemsRepository.findById(menuItems.getId());
        if (!optMenu.isPresent()){
            throw new MenuException("Menu Items is not found");
        }
        MenuItems item = optMenu.get();
        item.setItemName(menuItems.getItemName());
        item.setPrice(menuItems.getPrice());
        item.setRestaurantId(menuItems.getRestaurantId());
        return menuItemsRepository.save(item);
    }

    @Override
    public String deleteItem(Long id) {
        menuItemsRepository.deleteById(id);
        return "MenuItem deleted successfully.";
    }
}
