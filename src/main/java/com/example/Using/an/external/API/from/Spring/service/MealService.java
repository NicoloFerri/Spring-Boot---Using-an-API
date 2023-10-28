package com.example.Using.an.external.API.from.Spring.service;

import com.example.Using.an.external.API.from.Spring.entity.Meal;
import com.example.Using.an.external.API.from.Spring.repository.MealRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private Double MIN_SUMMER_TEMPERATURE;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Autowired
    public List<Meal> gelAllMeals (){
        return mealRepository.findAll();
    }

    public Optional<Meal> getMealById (Long id){
        return mealRepository.findById(id);
    }

    public Meal createMeal(Meal meal){
        return mealRepository.save(meal);
    }

    public Meal updateMealById(Long id,Meal meal) throws IllegalAccessException {
        if(mealRepository.existsById(id)){
            meal.setId(id);
            return mealRepository.save(meal);
        }else{
            throw new IllegalAccessException("Meal non trovato!");
        }
    }

    public void deleteMeal(Long id){
        mealRepository.deleteById(id);
    }

    public List<Meal> getSummerMeals() throws UnirestException {
        Double currentTemperatureInCentigrade =  getCurrentTemperatureInCentigrade();
        if(currentTemperatureInCentigrade>MIN_SUMMER_TEMPERATURE) return new ArrayList<>();
        return mealRepository.findByIsSummerMeal(true);
    }

    private Double getCurrentTemperatureInCentigrade() throws UnirestException{
        JSONObject response = Unirest.get("https://api.open-meteo.com/v1/forecast?latitude=44.8095&longitude=10.1773&hourly=temperature_2m")
                .asJson().getBody().getObject();

        return response.getJSONObject("current_weather").getDouble("temperature");
    }

}
