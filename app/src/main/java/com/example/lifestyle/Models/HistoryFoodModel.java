package com.example.lifestyle.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryFoodModel implements Serializable {
    int history_food_id;
    int user_id;
    double calorieTotal;
    String date;
    ArrayList<FoodModel> foods;


    public HistoryFoodModel() {
    }

    public HistoryFoodModel(int user_id, double calorieTotal, String date) {
        this.user_id = user_id;
        this.calorieTotal = calorieTotal;
        this.date = date;
    }


    public int getHistory_food_id() {
        return history_food_id;
    }

    public void setHistory_food_id(int history_food_id) {
        this.history_food_id = history_food_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getCalorieTotal() {
        return calorieTotal;
    }

    public void setCalorieTotal(double calorieTotal) {
        this.calorieTotal = calorieTotal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<FoodModel> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<FoodModel> foods) {
        this.foods = foods;
    }
}

//user// --history_food
            //  ---id документа
                //calorieTotal
                //date
                //--foods
                    //--food_id
            //  ---id документа2
                //calorieTotal
                //date
                //--foods
                    //--food_id
