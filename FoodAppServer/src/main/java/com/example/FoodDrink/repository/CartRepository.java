package com.example.FoodDrink.repository;

import com.example.FoodDrink.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUser_Id(String s);
}
