package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.OrderItemDTO;
import com.example.FoodDrink.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface OrderItemMapper {
    OrderItemDTO entityToDto(OrderItem orderItem);
    OrderItem dtoToEntity(OrderItemDTO orderItemDTO);
}
