package com.ordermanagementservice.service;

import com.ordermanagementservice.dto.request.OrderRequest;
import com.ordermanagementservice.dto.request.UpdateOrderRequest;
import com.ordermanagementservice.entity.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Orders> getAllOrders();

    Orders viewOrderById(Long id);

    Orders placeOrder(OrderRequest orderRequest);

    Orders updateOrder(UpdateOrderRequest updateRequest);

    String cancelOrder(Long id);

}
