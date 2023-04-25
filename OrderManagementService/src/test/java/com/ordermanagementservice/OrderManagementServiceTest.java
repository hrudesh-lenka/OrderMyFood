package com.ordermanagementservice;

import com.ordermanagementservice.advice.OrderException;
import com.ordermanagementservice.client.RestaurantClient;
import com.ordermanagementservice.dto.request.OrderRequest;
import com.ordermanagementservice.dto.request.UpdateOrderRequest;
import com.ordermanagementservice.entity.Orders;
import com.ordermanagementservice.entity.User;
import com.ordermanagementservice.repository.OrderRepository;
import com.ordermanagementservice.repository.UserRepository;
import com.ordermanagementservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class OrderManagementServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private RestaurantClient restaurantClient;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testGetAllOrders() {
        ArrayList<Orders> ordersList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(ordersList);
        List<Orders> actualAllOrders = orderServiceImpl.getAllOrders();
        assertSame(ordersList, actualAllOrders);
        assertTrue(actualAllOrders.isEmpty());
        verify(orderRepository).findAll();
    }


    @Test
    void testGetAllOrders2() {
        when(orderRepository.findAll()).thenThrow(new OrderException("An error occurred"));
        assertThrows(OrderException.class, () -> orderServiceImpl.getAllOrders());
        verify(orderRepository).findAll();
    }


    @Test
    void testViewOrderById() {
        User user = new User();
        user.setEmail("hrudesh12@gmail.com");
        user.setId(1L);
        user.setOrders(new ArrayList<>());
        user.setPassword("hadwadwa");
        user.setUserName("hrudesh12");

        Orders orders = new Orders();
        orders.setId(1L);
        orders.setOrderItems(new ArrayList<>());
        orders.setPaymentId("42");
        orders.setRestaurantName("Mughlai");
        orders.setStatus("Created");
        orders.setTotalPrice(BigDecimal.valueOf(42L));
        orders.setUser(user);
        Optional<Orders> ofResult = Optional.of(orders);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Orders actualViewOrderByIdResult = orderServiceImpl.viewOrderById(1L);
        assertSame(orders, actualViewOrderByIdResult);
        assertEquals("42", actualViewOrderByIdResult.getTotalPrice().toString());
        verify(orderRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testViewOrderById2() {
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(OrderException.class, () -> orderServiceImpl.viewOrderById(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testViewOrderById3() {
        when(orderRepository.findById(Mockito.<Long>any())).thenThrow(new OrderException("An error occurred"));
        assertThrows(OrderException.class, () -> orderServiceImpl.viewOrderById(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testPlaceOrder() {
        User user = new User();
        user.setEmail("hrudesh12@gmail.com");
        user.setId(1L);
        user.setOrders(new ArrayList<>());
        user.setPassword("hadwadwa");
        user.setUserName("hrudesh12");

        Orders orders = new Orders();
        orders.setId(1L);
        orders.setOrderItems(new ArrayList<>());
        orders.setPaymentId("42");
        orders.setRestaurantName("Mughlai");
        orders.setStatus("Created");
        orders.setTotalPrice(BigDecimal.valueOf(42L));
        orders.setUser(user);
        when(orderRepository.save(Mockito.<Orders>any())).thenReturn(orders);
        when(restaurantClient.getRestaurantNameById(Mockito.<Long>any())).thenReturn(Optional.of("42"));

        User user2 = new User();
        user2.setEmail("hrudesh201@gmail.com");
        user2.setId(1L);
        user2.setOrders(new ArrayList<>());
        user2.setPassword("asdasd");
        user2.setUserName("hrudesh2010");
        Optional<User> ofResult = Optional.of(user2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderItemRequests(new ArrayList<>());
        orderRequest.setRestaurantId(1L);
        orderRequest.setUserId(1L);
        Orders actualPlaceOrderResult = orderServiceImpl.placeOrder(orderRequest);
        assertSame(orders, actualPlaceOrderResult);
        assertEquals("42", actualPlaceOrderResult.getTotalPrice().toString());
        verify(orderRepository).save(Mockito.<Orders>any());
        verify(restaurantClient).getRestaurantNameById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testPlaceOrder2() {
        when(orderRepository.save(Mockito.<Orders>any())).thenThrow(new OrderException("An error occurred"));
        when(restaurantClient.getRestaurantNameById(Mockito.<Long>any())).thenReturn(Optional.of("42"));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderItemRequests(new ArrayList<>());
        orderRequest.setRestaurantId(1L);
        orderRequest.setUserId(1L);
        assertThrows(OrderException.class, () -> orderServiceImpl.placeOrder(orderRequest));
        verify(orderRepository).save(Mockito.<Orders>any());
        verify(restaurantClient).getRestaurantNameById(Mockito.<Long>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }



    @Test
    void testUpdateOrder() {
        User user = new User();
        user.setEmail("hrudesh12@gmail.com");
        user.setId(1L);
        user.setOrders(new ArrayList<>());
        user.setPassword("hadwadwa");
        user.setUserName("hrudesh12");

        Orders orders = new Orders();
        orders.setId(1L);
        orders.setOrderItems(new ArrayList<>());
        orders.setPaymentId("42");
        orders.setRestaurantName("Mughlai");
        orders.setStatus("Updated");
        orders.setTotalPrice(BigDecimal.valueOf(42L));
        orders.setUser(user);
        when(orderRepository.save(Mockito.<Orders>any())).thenReturn(orders);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        when(restaurantClient.getRestaurantNameById(Mockito.<Long>any())).thenReturn(Optional.of("42"));

        User user2 = new User();
        user2.setEmail("hrudesh12344@gmail.com");
        user2.setId(2L);
        user2.setOrders(new ArrayList<>());
        user2.setPassword("asdgefe");
        user2.setUserName("hrudesh1234");
        Optional<User> ofResult = Optional.of(user2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        UpdateOrderRequest updateRequest = new UpdateOrderRequest();
        updateRequest.setOrderId(1L);
        updateRequest.setOrderItemRequests(new ArrayList<>());
        updateRequest.setRestaurantId(1L);
        updateRequest.setUserId(1L);
        assertThrows(OrderException.class, () -> orderServiceImpl.updateOrder(updateRequest));
        verify(orderRepository).findById(Mockito.<Long>any());
    }



    @Test
    void testCancelOrder() {
        User user = new User();
        user.setEmail("hrudesh12@gmail.com");
        user.setId(1L);
        user.setOrders(new ArrayList<>());
        user.setPassword("hadwadwa");
        user.setUserName("hrudesh12");

        Orders orders = new Orders();
        orders.setId(1L);
        orders.setOrderItems(new ArrayList<>());
        orders.setPaymentId("42");
        orders.setRestaurantName("Mughlai");
        orders.setStatus("Cancelled");
        orders.setTotalPrice(BigDecimal.valueOf(42L));
        orders.setUser(user);
        Optional<Orders> ofResult = Optional.of(orders);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertEquals("Order has been cancelled successfully. Refund will be processed in 24 hours",
                orderServiceImpl.cancelOrder(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testCancelOrder2() {
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(OrderException.class, () -> orderServiceImpl.cancelOrder(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testCancelOrder3() {
        when(orderRepository.findById(Mockito.<Long>any())).thenThrow(new OrderException("An error occurred"));
        assertThrows(OrderException.class, () -> orderServiceImpl.cancelOrder(1L));
        verify(orderRepository).findById(Mockito.<Long>any());
    }
}
