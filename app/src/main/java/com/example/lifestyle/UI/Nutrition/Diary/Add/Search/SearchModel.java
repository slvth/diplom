package com.example.lifestyle.UI.Nutrition.Diary.Add.Search;

public class SearchModel {


    String name;
    public SearchModel(){}
    Long kal,carbohydrate,fat,protein;

    public SearchModel(String name, Long kal, Long carbohydrate, Long fat, Long protein) {
        this.name = name;
        this.kal = kal;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.protein = protein;
    }

    public Long getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Long carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Long getFat() {
        return fat;
    }

    public void setFat(Long fat) {
        this.fat = fat;
    }

    public Long getProtein() {
        return protein;
    }

    public void setProtein(Long protein) {
        this.protein = protein;
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
}
