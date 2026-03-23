package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.ReviewDTO;
import com.example.FoodDrink.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO entityToDto(Review review);
    Review dtoToEntity(ReviewDTO reviewDTO);
}
