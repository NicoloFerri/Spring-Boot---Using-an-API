package com.example.Using.an.external.API.from.Spring.repository;

import com.example.Using.an.external.API.from.Spring.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal,Long> {
    List<Meal> findByIsSummerMeal(boolean isSummerMeal);
}
