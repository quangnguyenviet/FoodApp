package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.CartDTO;
import com.example.FoodDrink.dto.response.Response;

public interface CartService {
    Response<?> addItemToCart(CartDTO cartDTO);
    Response<?> incrementItem(String menuId);
    Response<?> decrementItem(String menuId);
    Response<?> removeItem(String cartItemId);
    Response<CartDTO> getShoppingCart();
    Response<?> clearShoppingCart();
}