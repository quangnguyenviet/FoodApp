package com.example.FoodDrink.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields during serialization
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties during deserialization
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    private String profileUrl;

    //// Write-only: Included when receiving data, excluded when sending data
    /// //Only used for writing (deserialization), ignored during reading (serialization)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Boolean active;

    private String address;

    private List<RoleDTO> roles;

    private MultipartFile imageFile;
}
