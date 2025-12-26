package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.OrderDTO;
import com.example.FoodDrink.dto.OrderItemDTO;
import com.example.FoodDrink.dto.response.Response;
import com.example.FoodDrink.enums.OrderStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Response<?> placeOrderFromCart();
    Response<OrderDTO> getOrderById(String id);
    Response<Page<OrderDTO>> getAllOrders(OrderStatus orderStatus, int page, int size);
    Response<List<OrderDTO>> getOrdersOfUser();
    Response<OrderItemDTO> getOrderItemById(String orderItemId);
    Response<OrderDTO> updateOrderStatus(OrderDTO orderDTO);
    Response<Long> countUniqueCustomers();
}