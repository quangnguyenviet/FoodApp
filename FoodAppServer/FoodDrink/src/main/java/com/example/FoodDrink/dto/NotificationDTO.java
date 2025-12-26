package com.example.FoodDrink.dto;

import com.example.FoodDrink.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields during serialization
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties during deserialization
@Builder
public class NotificationDTO {

    private String id;

    private String subject;

    @NotBlank(message = "Recipient cannot be blank")
    private String recipient;

    private String body;

    private NotificationType type;

    private LocalDateTime createdAt;

    private boolean html;
}
