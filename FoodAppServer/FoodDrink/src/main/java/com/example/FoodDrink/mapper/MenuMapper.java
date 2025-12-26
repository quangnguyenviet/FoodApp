package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.MenuDTO;
import com.example.FoodDrink.entity.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    Menu dtoToEntity(MenuDTO menuDTO);
    MenuDTO entityToDto(Menu menu);
}
