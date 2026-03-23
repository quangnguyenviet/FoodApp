package com.example.FoodDrink.service;


import com.example.FoodDrink.dto.ReviewDTO;
import com.example.FoodDrink.dto.response.Response;

import java.util.List;

public interface ReviewService {
    Response<ReviewDTO> createReview(ReviewDTO reviewDTO);
    Response<List<ReviewDTO>> getReviewsForMenu(String menuId);
    Response<Double> getAverageRating(String menuId);
}
