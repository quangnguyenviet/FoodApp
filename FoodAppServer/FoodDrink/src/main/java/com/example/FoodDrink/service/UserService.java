package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.UserDTO;
import com.example.FoodDrink.dto.response.Response;
import com.example.FoodDrink.entity.User;

import java.util.List;

public interface UserService {

    User getCurrentLoggedInUser();

    Response<List<UserDTO>> getAllUsers();

    Response<UserDTO> getOwnAccountDetails();

    Response<?> updateOwnAccount(UserDTO userDTO);

    Response<?> deactivateOwnAccount();

}
