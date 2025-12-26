package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.MenuDTO;
import com.example.FoodDrink.dto.response.Response;

import java.util.List;

public interface MenuService {

    Response<MenuDTO> createMenu(MenuDTO menuDTO);
    Response<MenuDTO> updateMenu(MenuDTO menuDTO);
    Response<MenuDTO> getMenuById(String id);
    Response<?> deleteMenu(String id);
    Response<List<MenuDTO>> getMenus(String categoryId, String search);

}