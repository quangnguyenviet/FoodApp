package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.UserDTO;
import com.example.FoodDrink.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    UserDTO toDTO(User entity);
    User toEntity(UserDTO dto);
}
