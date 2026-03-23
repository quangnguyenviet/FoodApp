package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.RoleDTO;
import com.example.FoodDrink.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role dtoToEntity(RoleDTO roleDTO);
    RoleDTO entityToDto(Role role);
}
