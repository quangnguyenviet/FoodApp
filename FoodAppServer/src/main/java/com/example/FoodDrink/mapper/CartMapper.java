package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.CartDTO;
import com.example.FoodDrink.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    CartDTO entityToDto(Cart cart);
    Cart dtoToEntity(CartDTO cartDTO);
}
