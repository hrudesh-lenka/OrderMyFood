package com.ordermanagementservice.service.impl;

import com.ordermanagementservice.advice.OrderException;
import com.ordermanagementservice.client.RestaurantClient;
import com.ordermanagementservice.dto.MenuItem;
import com.ordermanagementservice.dto.request.OrderItemRequest;
import com.ordermanagementservice.dto.request.OrderRequest;
import com.ordermanagementservice.dto.request.UpdateOrderRequest;
import com.ordermanagementservice.entity.Orders;
import com.ordermanagementservice.entity.OrderItems;
import com.ordermanagementservice.entity.User;
import com.ordermanagementservice.repository.OrderRepository;
import com.ordermanagementservice.repository.UserRepository;
import com.ordermanagementservice.service.OrderService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private RestaurantClient restaurantClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders viewOrderById(Long id) {
        Optional<Orders> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()){
            throw new OrderException("Order not found exception");
        }
        return optionalOrder.get();
    }

    @Override
    public Orders placeOrder(OrderRequest orderRequest) {
        if (orderRequest == null) {
            throw new OrderException("Invalid Request");
        }
        Optional<User> user = userRepository.findById(orderRequest.getUserId());
        List<OrderItemRequest> orderItemRequests = orderRequest.getOrderItemRequests();
        Optional<String> restaurantName = restaurantClient.getRestaurantNameById(orderRequest.getRestaurantId());
        log.info("Restaurant Name from restaurant service : {}",restaurantName.get());
        List<OrderItems> orderItems = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        orderItemRequests.stream().forEach( x -> {
            Optional<MenuItem> menu = restaurantClient.getItemById(x.getItemId()).getBody();
            log.info("Menu item from restaurant service : {}",menu);
            OrderItems orderItem = new OrderItems();
//            orderItem.setId((long) (Math.random() * 1000));
            orderItem.setItemId(menu.get().getId());
            orderItem.setItemName(menu.get().getItemName());
            orderItem.setQuantity(x.getQuantity());
            BigDecimal totalPr = menu.get().getPrice().multiply(BigDecimal.valueOf(x.getQuantity()));
            orderItem.setPrice(totalPr);
            log.info("The total order-item price till now {}",totalPr);
            orderItems.add(orderItem);
        });
        for(OrderItems item : orderItems){
            totalPrice = totalPrice.add(item.getPrice());
        }
        log.info("The total order price till now {}",totalPrice);
        Orders orders = new Orders();
        orders.setRestaurantName(restaurantName.get());
        orders.setPaymentId(UUID.randomUUID().toString());
        orders.setUser(user.get());
        orders.setOrderItems(orderItems);
        orders.setTotalPrice(totalPrice);
        orders.setStatus("Created");
        log.info("Payment has been realized and the order has been created");
        log.info("Created order {}", orders);
        return orderRepository.save(orders);
    }

    @Override
    public Orders updateOrder(UpdateOrderRequest updateRequest) {
        if (updateRequest == null) {
            throw new OrderException("Invalid Request");
        }
        Optional<Orders> getOrder = orderRepository.findById(updateRequest.getOrderId());
        if(!getOrder.isPresent()){
            throw new OrderException("No Order with this id found");
        }
        Optional<User> user = userRepository.findById(updateRequest.getUserId());
        List<OrderItemRequest> orderItemRequests = updateRequest.getOrderItemRequests();
        Optional<String> restaurantName = restaurantClient.getRestaurantNameById(updateRequest.getRestaurantId());
        List<OrderItems> orderItems = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        orderItemRequests.stream().forEach( x -> {
            Optional<MenuItem> menu = restaurantClient.getItemById(x.getItemId()).getBody();
            OrderItems orderItem = new OrderItems();
            orderItem.setItemId(menu.get().getId());
            orderItem.setItemName(menu.get().getItemName());
            orderItem.setQuantity(x.getQuantity());
            BigDecimal totalPr = menu.get().getPrice().multiply(BigDecimal.valueOf(x.getQuantity()));
            orderItem.setPrice(totalPr);
            orderItems.add(orderItem);
        });

        for(OrderItems item : orderItems){
            totalPrice = totalPrice.add(item.getPrice());
        }
        Orders orders = getOrder.get();
        orders.setRestaurantName(restaurantName.get());
        orders.setPaymentId(UUID.randomUUID().toString());
        orders.setUser(user.get());
        orders.setOrderItems(orderItems);
        orders.setTotalPrice(totalPrice);
        orders.setStatus("Updated");
        log.info("Payment has been realized and the order has been created");
        return orderRepository.save(orders);
    }

    @Override
    public String cancelOrder(Long id) {
        Optional<Orders> getOrder = orderRepository.findById(id);
        if(!getOrder.isPresent()){
            throw new OrderException("No Order by this id");
        }
        getOrder.get().setStatus("Cancelled");
        return "Order has been cancelled successfully. Refund will be processed in 24 hours";
    }
}
