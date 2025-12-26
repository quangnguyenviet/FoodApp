package com.example.FoodDrink.repository;

import com.example.FoodDrink.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {


    @Query("SELECT CASE WHEN COUNT(oi) > 0 THEN true ELSE false END " +
            "FROM OrderItem oi " +
            "WHERE oi.order.id = :orderId AND oi.menu.id = :menuId")
    boolean existsByOrderIdAndMenuId(
            @Param("orderId") String orderId,
            @Param("menuId") String menuId);

}