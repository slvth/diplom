package com.example.lifestyle.Models;

import java.io.Serializable;

public class TotalHistoryWorkoutModel implements Serializable {
    int user_id;
    int activity_id;
    int workout_id;
    String name;
    int time_second;
    double calorie;
    String date;

    public TotalHistoryWorkoutModel() {
    }

    public TotalHistoryWorkoutModel(int activity_id, int workout_id, int time_second, double calorie, String date) {
        this.activity_id = activity_id;
        this.workout_id = workout_id;
        this.time_second = time_second;
        this.calorie = calorie;
        this.date = date;
    }

    public TotalHistoryWorkoutModel(int user_id, int activity_id, int workout_id, int time_second, double calorie, String date) {
        this.user_id = user_id;
        this.activity_id = activity_id;
        this.workout_id = workout_id;
        this.time_second = time_second;
        this.calorie = calorie;
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }

    public int getTime_second() {
        return time_second;
    }

    public void setTime_second(int time_second) {
        this.time_second = time_second;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
