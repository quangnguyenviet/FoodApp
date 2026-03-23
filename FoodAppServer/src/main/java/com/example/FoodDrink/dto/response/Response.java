package com.example.FoodDrink.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // don't include null fields in JSON response
public class Response <T> {
    private int statusCode;
    private String message;
    private T data;
    private Map<String, Serializable> metaData;
}
