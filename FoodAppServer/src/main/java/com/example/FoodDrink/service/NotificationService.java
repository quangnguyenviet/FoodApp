package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.NotificationDTO;

public interface NotificationService {
    void sendEmail(NotificationDTO notificationDTO);
}
