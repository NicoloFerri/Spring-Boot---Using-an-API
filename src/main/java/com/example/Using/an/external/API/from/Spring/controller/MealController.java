package com.example.Using.an.external.API.from.Spring.controller;

import com.example.Using.an.external.API.from.Spring.entity.Meal;
import com.example.Using.an.external.API.from.Spring.service.MealService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meal")
public class MealController {

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/getAllMeals")
    public List<Meal> getAllMeals(){
        return mealService.gelAllMeals();
    }

    @GetMapping("/getMealById/{id}")
    public Optional<Meal> getMealById (@PathVariable Long id){
        return mealService.getMealById(id);
    }

    @PostMapping("/insertMeal")
    public Meal insertMeal(@RequestBody Meal meal){
        return mealService.createMeal(meal);
    }

    @GetMapping("/summerMeals")
    public ResponseEntity<List<Meal>> getSummerMeals() throws UnirestException {
        return ResponseEntity.ok(mealService.getSummerMeals());
    }
}
