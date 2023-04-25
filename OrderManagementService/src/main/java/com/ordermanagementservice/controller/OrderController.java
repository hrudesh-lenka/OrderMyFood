package com.ordermanagementservice.controller;

import com.ordermanagementservice.dto.request.OrderRequest;
import com.ordermanagementservice.dto.request.UpdateOrderRequest;
import com.ordermanagementservice.entity.Orders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/order")
public interface OrderController {

    @GetMapping("/{orderId}")
    ResponseEntity<Orders> viewOrderById(@PathVariable("orderId") Long id);

    @PostMapping("/create")
    ResponseEntity<Orders> placeOrder(@RequestBody OrderRequest orderRequest);

    @PutMapping("/update")
    ResponseEntity<Orders> updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest);

    @DeleteMapping("/cancel/{id}")
    ResponseEntity<String> cancelOrder(@PathVariable Long id);
}
