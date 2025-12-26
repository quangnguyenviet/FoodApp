package com.example.FoodDrink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDTO {
    private String id;
    private List<CartItemDTO> cartItems;
    private String menuId;
    private int quantity;
    private BigDecimal totalAmount;
}
