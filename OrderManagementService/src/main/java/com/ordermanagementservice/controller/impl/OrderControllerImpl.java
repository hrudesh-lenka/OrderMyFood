package com.ordermanagementservice.controller.impl;

import com.ordermanagementservice.controller.OrderController;
import com.ordermanagementservice.dto.request.OrderRequest;
import com.ordermanagementservice.dto.request.UpdateOrderRequest;
import com.ordermanagementservice.entity.Orders;
import com.ordermanagementservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;


    @Override
    public ResponseEntity<Orders> viewOrderById(Long id) {
        return new ResponseEntity<>(orderService.viewOrderById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Orders> placeOrder(OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Orders> updateOrder(UpdateOrderRequest updateOrderRequest) {
        return new ResponseEntity<>(orderService.updateOrder(updateOrderRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cancelOrder(Long id) {
        return new ResponseEntity<>(orderService.cancelOrder(id), HttpStatus.OK);
    }
}
