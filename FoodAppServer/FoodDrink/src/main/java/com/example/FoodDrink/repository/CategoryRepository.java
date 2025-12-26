package com.example.FoodDrink.repository;

import com.example.FoodDrink.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
