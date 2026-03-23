package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.CartItemDTO;
import com.example.FoodDrink.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface CartItemMapper {
    CartItemDTO entityToDto(CartItem cartItem);
    CartItem dtoToEntity(CartItemDTO cartItemDTO);
}
