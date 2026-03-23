package com.example.FoodDrink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemDTO {

    private String id;

    private int quantity;

    private Long menuId;

    private MenuDTO menu;

    private BigDecimal pricePerUnit;

    private BigDecimal subtotal;
}
