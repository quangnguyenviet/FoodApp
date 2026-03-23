package com.example.FoodDrink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItemDTO {
    private String id;
    private MenuDTO menu;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal subtotal;
    private Instant createdAt;
}
