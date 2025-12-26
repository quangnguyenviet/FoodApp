package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.CategoryDTO;
import com.example.FoodDrink.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToEntity(CategoryDTO categoryDTO);
    CategoryDTO entityToDto(Category category);
}
