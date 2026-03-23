package com.example.FoodDrink.repository;

import com.example.FoodDrink.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
