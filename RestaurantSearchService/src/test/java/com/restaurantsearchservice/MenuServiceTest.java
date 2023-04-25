package com.restaurantsearchservice;

import com.restaurantsearchservice.advice.MenuException;
import com.restaurantsearchservice.entity.MenuItems;
import com.restaurantsearchservice.repository.MenuItemsRepository;
import com.restaurantsearchservice.service.impl.MenuItemsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {MenuItemsServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class MenuServiceTest {

    @MockBean
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private MenuItemsServiceImpl menuItemsServiceImpl;

    @Test
    void testGetAllItems() {
        ArrayList<MenuItems> menuItemsList = new ArrayList<>();
        when(menuItemsRepository.findAll()).thenReturn(menuItemsList);
        List<MenuItems> actualAllItems = menuItemsServiceImpl.getAllItems();
        assertSame(menuItemsList, actualAllItems);
        assertTrue(actualAllItems.isEmpty());
        verify(menuItemsRepository).findAll();
    }


    @Test
    void testGetAllItems2() {
        when(menuItemsRepository.findAll()).thenThrow(new MenuException("An error occurred"));
        assertThrows(MenuException.class, () -> menuItemsServiceImpl.getAllItems());
        verify(menuItemsRepository).findAll();
    }


    @Test
    void testGetItemById() {
        MenuItems menuItems = new MenuItems();
        menuItems.setId(1L);
        menuItems.setItemName("Paneer Pakoda");
        menuItems.setPrice(10.0d);
        menuItems.setRestaurantId(1L);
        Optional<MenuItems> ofResult = Optional.of(menuItems);
        when(menuItemsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<MenuItems> actualItemById = menuItemsServiceImpl.getItemById(1L);
        assertSame(ofResult, actualItemById);
        assertTrue(actualItemById.isPresent());
        verify(menuItemsRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testGetItemById2() {
        when(menuItemsRepository.findById(Mockito.<Long>any())).thenThrow(new MenuException("An error occurred"));
        assertThrows(MenuException.class, () -> menuItemsServiceImpl.getItemById(1L));
        verify(menuItemsRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testSaveItem() {
        MenuItems menuItems = new MenuItems();
        menuItems.setId(1L);
        menuItems.setItemName("Paneer Pakoda");
        menuItems.setPrice(10.0d);
        menuItems.setRestaurantId(1L);
        when(menuItemsRepository.save(Mockito.<MenuItems>any())).thenReturn(menuItems);

        MenuItems menuItems2 = new MenuItems();
        menuItems2.setId(2L);
        menuItems2.setItemName("Chicken Pakoda");
        menuItems2.setPrice(130.0d);
        menuItems2.setRestaurantId(1L);
        assertSame(menuItems, menuItemsServiceImpl.saveItem(menuItems2));
        verify(menuItemsRepository).save(Mockito.<MenuItems>any());
    }


    @Test
    void testSaveItem2() {
        when(menuItemsRepository.save(Mockito.<MenuItems>any())).thenThrow(new MenuException("An error occurred"));

        MenuItems menuItems = new MenuItems();
        menuItems.setId(1L);
        menuItems.setItemName("Chowmein");
        menuItems.setPrice(120.0d);
        menuItems.setRestaurantId(1L);
        assertThrows(MenuException.class, () -> menuItemsServiceImpl.saveItem(menuItems));
        verify(menuItemsRepository).save(Mockito.<MenuItems>any());
    }


    @Test
    void testUpdateItem() {
        MenuItems menuItems = new MenuItems();
        menuItems.setId(1L);
        menuItems.setItemName("Veg Biriyani");
        menuItems.setPrice(130.0d);
        menuItems.setRestaurantId(1L);
        Optional<MenuItems> ofResult = Optional.of(menuItems);

        MenuItems menuItems2 = new MenuItems();
        menuItems2.setId(2L);
        menuItems2.setItemName("Chicken Biriyani");
        menuItems2.setPrice(310.0d);
        menuItems2.setRestaurantId(1L);
        when(menuItemsRepository.save(Mockito.<MenuItems>any())).thenReturn(menuItems2);
        when(menuItemsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MenuItems menuItems3 = new MenuItems();
        menuItems3.setId(3L);
        menuItems3.setItemName("Paneer Biriyani");
        menuItems3.setPrice(250.0d);
        menuItems3.setRestaurantId(1L);
        assertSame(menuItems2, menuItemsServiceImpl.updateItem(menuItems3));
        verify(menuItemsRepository).save(Mockito.<MenuItems>any());
        verify(menuItemsRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testUpdateItem2() {
        MenuItems menuItems = new MenuItems();
        menuItems.setId(1L);
        menuItems.setItemName("Paneer Biriyani");
        menuItems.setPrice(150.0d);
        menuItems.setRestaurantId(1L);
        Optional<MenuItems> ofResult = Optional.of(menuItems);
        when(menuItemsRepository.save(Mockito.<MenuItems>any())).thenThrow(new MenuException("An error occurred"));
        when(menuItemsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MenuItems menuItems2 = new MenuItems();
        menuItems2.setId(2L);
        menuItems2.setItemName("Chicken Biriyani");
        menuItems2.setPrice(150.0d);
        menuItems2.setRestaurantId(1L);
        assertThrows(MenuException.class, () -> menuItemsServiceImpl.updateItem(menuItems2));
        verify(menuItemsRepository).save(Mockito.<MenuItems>any());
        verify(menuItemsRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testUpdateItem3() {
        MenuItems menuItems = new MenuItems();
        menuItems.setId(1L);
        menuItems.setItemName("Chicken Biriyani");
        menuItems.setPrice(10.0d);
        menuItems.setRestaurantId(1L);
        when(menuItemsRepository.save(Mockito.<MenuItems>any())).thenReturn(menuItems);
        when(menuItemsRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        MenuItems menuItems2 = new MenuItems();
        menuItems2.setId(2L);
        menuItems2.setItemName("Paneer Biriyani");
        menuItems2.setPrice(140.0d);
        menuItems2.setRestaurantId(1L);
        assertThrows(MenuException.class, () -> menuItemsServiceImpl.updateItem(menuItems2));
        verify(menuItemsRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testDeleteItem() {
        doNothing().when(menuItemsRepository).deleteById(Mockito.<Long>any());
        assertEquals("MenuItem deleted successfully.", menuItemsServiceImpl.deleteItem(1L));
        verify(menuItemsRepository).deleteById(Mockito.<Long>any());
    }


    @Test
    void testDeleteItem2() {
        doThrow(new MenuException("An error occurred")).when(menuItemsRepository).deleteById(Mockito.<Long>any());
        assertThrows(MenuException.class, () -> menuItemsServiceImpl.deleteItem(1L));
        verify(menuItemsRepository).deleteById(Mockito.<Long>any());
    }
}
