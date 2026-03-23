package com.example.FoodDrink.repository;

import com.example.FoodDrink.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

}
