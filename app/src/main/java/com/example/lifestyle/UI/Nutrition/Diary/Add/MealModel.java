package com.example.lifestyle.UI.Nutrition.Diary.Add;

public class MealModel {

    String Description_food, name;

    public MealModel() {
    }

    Long kal_food, kal;
    Long Time;
    Long carbohydrates_food, fat_food, proteins_food, portion;

    public MealModel(String description_food, String name, Long kal_food, Long portion, Long kal, Long time, Long carbohydrates_food, Long fat_food, Long proteins_food) {
        Description_food = description_food;
        this.name = name;
        this.kal_food = kal_food;
        this.kal = kal;
        Time = time;
        this.carbohydrates_food = carbohydrates_food;
        this.fat_food = fat_food;
        this.portion = portion;
        this.proteins_food = proteins_food;
    }

    public Long getPortion() {
        return portion;
    }

    public void setPortion(Long portion) {
        this.portion = portion;
    }

    public Long getCarbohydrates_food() {
        return carbohydrates_food;
    }

    public void setCarbohydrates_food(Long carbohydrates_food) {
        this.carbohydrates_food = carbohydrates_food;
    }

    public Long getFat_food() {
        return fat_food;
    }

    public void setFat_food(Long fat_food) {
        this.fat_food = fat_food;
    }

    public Long getProteins_food() {
        return proteins_food;
    }

    public void setProteins_food(Long proteins_food) {
        this.proteins_food = proteins_food;
    }

    public String getDescription_food() {
        return Description_food;
    }

    public void setDescription_food(String description_food) {
        Description_food = description_food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getKal_food() {
        return kal_food;
    }

    public void setKal_food(Long kal_food) {
        this.kal_food = kal_food;
    }

    public Long getKal() {
        return kal;
    }

    public void setKal(Long kal) {
        this.kal = kal;
    }

    public Long getTime() {
        return Time;
    }

    public void setTime(Long time) {
        Time = time;
    }
}