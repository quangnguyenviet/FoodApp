package com.example.FoodDrink.service;

import com.example.FoodDrink.dto.RoleDTO;
import com.example.FoodDrink.dto.response.Response;

import java.util.List;

public interface RoleService {
    Response<RoleDTO> createRole(RoleDTO roleDTO);
    Response<RoleDTO> updateRole(RoleDTO roleDTO);
    Response<List<RoleDTO>> getAllRoles();
    Response<?> deleteRole(String id);
    Response<RoleDTO> getRoleById(Long id);
}
