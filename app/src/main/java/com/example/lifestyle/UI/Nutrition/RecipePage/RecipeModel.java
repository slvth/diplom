package com.example.lifestyle.UI.Nutrition.RecipePage;

public class RecipeModel {

    String name, img_meal, description1, description2, description3,complexity;

    public RecipeModel() {
    }

    Long kal, time;

    public RecipeModel(String name, Long kal, Long time, String img_meal, String description1, String description2, String description3,String complexity) {
        this.name = name;
        this.kal = kal;
        this.time = time;
        this.img_meal = img_meal;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.complexity = complexity;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getImg_meal() {
        return img_meal;
    }

    public void setImg_meal(String img_meal) {
        this.img_meal = img_meal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getKal() {
        return kal;
    }

    public void setKal(Long kal) {
        this.kal = kal;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
