package com.example.FoodDrink.repository;

import com.example.FoodDrink.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findByMenuIdOrderByIdDesc(String menuId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.menu.id = :menuId")
    Double calculateAverageRatingByMenuId(@Param("menuId") String menuId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Review r " +
            "WHERE r.user.id = :userId AND r.menu.id = :menuId AND r.orderId = :orderId")
    boolean existsByUserIdAndMenuIdAndOrderId(
            @Param("userId") String userId,
            @Param("menuId") String menuId,
            @Param("orderId") String orderId);

}