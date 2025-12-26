package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.OrderDTO;
import com.example.FoodDrink.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    Order dtoToEntity(OrderDTO orderDTO);
    OrderDTO entityToDto(Order order);
}
