package com.example.FoodDrink.service.impl;

import com.example.FoodDrink.dto.RoleDTO;
import com.example.FoodDrink.dto.response.Response;
import com.example.FoodDrink.entity.Role;
import com.example.FoodDrink.exceptions.BadRequestException;
import com.example.FoodDrink.exceptions.NotFoundException;
import com.example.FoodDrink.mapper.RoleMapper;
import com.example.FoodDrink.repository.RoleRepository;
import com.example.FoodDrink.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Response<RoleDTO> createRole(RoleDTO roleDTO) {
        Role role = roleMapper.dtoToEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        return Response.<RoleDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role created successfully")
                .data(roleMapper.entityToDto(savedRole))
                .build();
    }

    @Override
    public Response<RoleDTO> updateRole(RoleDTO roleDTO) {
        Role existingRole = roleRepository.findById(roleDTO.getId())
                .orElseThrow(
                        () -> new NotFoundException("Role not found with id: " + roleDTO.getId())
                );
        if(roleRepository.findByName(roleDTO.getName()).isPresent()){
            throw new BadRequestException("Role already exists with name: " + roleDTO.getName());
        }
        existingRole.setName(roleDTO.getName());
        Role updatedRole = roleRepository.save(existingRole);
        return Response.<RoleDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role updated successfully")
                .data(roleMapper.entityToDto(updatedRole))
                .build();

    }

    @Override
    public Response<List<RoleDTO>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = roles.stream()
                .map(role -> roleMapper.entityToDto(role))
                .toList();
        return Response.<List<RoleDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Roles retrieved successfully")
                .data(roleDTOS)
                .build();
    }

    @Override
    public Response<?> deleteRole(String id) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Role does not exists");
        }

        roleRepository.deleteById(id);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role deleted successfully")
                .build();
    }

    @Override
    public Response<RoleDTO> getRoleById(Long id) {
        return null;
    }
}
