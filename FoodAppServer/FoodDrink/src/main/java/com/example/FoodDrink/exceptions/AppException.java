package com.example.FoodDrink.exceptions;

import com.example.FoodDrink.enums.ErrorCode;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    private final ErrorCode errorCode;
}
