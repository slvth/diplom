package com.example.lifestyle.Models;

import java.io.Serializable;

public class ActivityModel implements Serializable {

    int activity_id;
    String name;
    int calorie_hour;
    int image;
    String imageURL;

    public ActivityModel() {
    }

    public ActivityModel(String name, int calorie_hour, int image, String imageURL) {
        this.name = name;
        this.calorie_hour = calorie_hour;
        this.image = image;
        this.imageURL = imageURL;
    }

    public ActivityModel(int activity_id, String name, int calorie_hour, int image, String imageURL) {
        this.activity_id = activity_id;
        this.name = name;
        this.calorie_hour = calorie_hour;
        this.image = image;
        this.imageURL = imageURL;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie_hour() {
        return calorie_hour;
    }

    public void setCalorie_hour(int calorie_hour) {
        this.calorie_hour = calorie_hour;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
