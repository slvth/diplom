package com.example.lifestyle.Models;

import java.io.Serializable;

public class ExerciseModel implements Serializable {
    int id;
    String name;
    String desc;
    int count;
    int time_second;
    int image;
    int gifs;

    public ExerciseModel(){}

    public ExerciseModel(int id, String name, String desc, int count, int time, int image, int gifs) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.count = count;
        this.time_second = time;
        this.image = image;
        this.gifs = gifs;
    }


    public ExerciseModel( String name, String desc, int count, int time, int image, int gifs) {
        this.name = name;
        this.desc = desc;
        this.count = count;
        this.time_second = time;
        this.image = image;
        this.gifs = gifs;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTime_second() {
        return time_second;
    }

    public void setTime_second(int time) {
        this.time_second = time;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getGifs() {
        return gifs;
    }

    public void setGifs(int gifs) {
        this.gifs = gifs;
    }
}
