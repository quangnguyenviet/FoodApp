package com.example.FoodDrink.service;


import com.example.FoodDrink.dto.CategoryDTO;
import com.example.FoodDrink.dto.response.Response;

import java.util.List;

public interface CategoryService {

    Response<CategoryDTO> addCategory(CategoryDTO categoryDTO);

    Response<CategoryDTO> updateCategory(CategoryDTO categoryDTO);

    Response<CategoryDTO> getCategoryById(String id);

    Response<List<CategoryDTO>> getAllCategories();

    Response<?> deleteCategory(String id);
}