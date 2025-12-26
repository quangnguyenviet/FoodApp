package com.example.FoodDrink.service;


import com.example.FoodDrink.dto.PaymentDTO;
import com.example.FoodDrink.dto.response.Response;

import java.util.List;

public interface PaymentService {

    Response<?> initializePayment(PaymentDTO paymentDTO);
    void updatePaymentForOrder(PaymentDTO paymentDTO);
    Response<List<PaymentDTO>> getAllPayments();
    Response<PaymentDTO> getPaymentById(String paymentId);

}