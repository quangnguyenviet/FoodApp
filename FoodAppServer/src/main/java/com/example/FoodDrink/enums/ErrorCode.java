package com.example.FoodDrink.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_FOUND(1001, "User not found", HttpStatusCode.valueOf(404)),
    INVALID_REQUEST(1002, "Invalid request", HttpStatusCode.valueOf(400)),
    PAYMENT_FAILED(1003, "Payment processing failed", HttpStatusCode.valueOf(502)),
    UNAUTHORIZED_ACCESS(1004, "Unauthorized access", HttpStatusCode.valueOf(401)),
    MISSING_CONTACT_INFORMATION(1005, "Missing contact information", HttpStatusCode.valueOf(400));
    private final int statusCode;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
