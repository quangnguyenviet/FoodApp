package com.example.FoodDrink.repository;

import com.example.FoodDrink.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, String> {
}
