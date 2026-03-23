package com.example.FoodDrink.mapper;

import com.example.FoodDrink.dto.PaymentDTO;
import com.example.FoodDrink.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, UserMapper.class})
public interface PaymentMapper {
    PaymentDTO entityToDto(Payment payment);
    Payment dtoToEntity(PaymentDTO paymentDTO);
}
