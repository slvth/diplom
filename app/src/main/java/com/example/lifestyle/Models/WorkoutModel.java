package com.example.lifestyle.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutModel implements Serializable {
    int id;
    String name;
    int time;
    int calorie;
    int status;
    String desc;
    ArrayList<ExerciseModel> exercices;

    public WorkoutModel() {}

    public WorkoutModel(int id, String name, int time,
                        int calorie, int status,
                        ArrayList<ExerciseModel> exercices,
                        String desc)
    {
        this.id = id;
        this.name = name;
        this.time = time;
        this.calorie = calorie;
        this.status = status;
        this.desc = desc;
        this.exercices = exercices;
    }

    public WorkoutModel(String name, int time, int calorie, int status, String desc) {
        this.name = name;
        this.time = time;
        this.calorie = calorie;
        this.status = status;
        this.desc = desc;
    }

    public WorkoutModel(int id, String name, int time, int calorie, int status, String desc) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.calorie = calorie;
        this.status = status;
        this.desc = desc;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public ArrayList<ExerciseModel> getExercices() {
        return exercices;
    }

    public void setExercices(ArrayList<ExerciseModel> exercices) {
        this.exercices = exercices;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
