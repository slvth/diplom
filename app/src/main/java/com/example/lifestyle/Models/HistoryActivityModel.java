package com.example.lifestyle.Models;

import java.io.Serializable;

public class HistoryActivityModel implements Serializable {

    int history_activity_id;
    int user_id;
    int activity_id;
    int time_second;
    double calorie;
    String date;

    public HistoryActivityModel() {
    }

    public HistoryActivityModel(int user_id, int activity_id, int time_second, double calorie, String date) {
        this.user_id = user_id;
        this.activity_id = activity_id;
        this.time_second = time_second;
        this.calorie = calorie;
        this.date = date;
    }

    public HistoryActivityModel(int history_activity_id, int user_id, int activity_id, int time_second, double calorie, String date) {
        this.history_activity_id = history_activity_id;
        this.user_id = user_id;
        this.activity_id = activity_id;
        this.time_second = time_second;
        this.calorie = calorie;
        this.date = date;
    }

    public int getHistory_activity_id() {
        return history_activity_id;
    }

    public void setHistory_activity_id(int history_activity_id) {
        this.history_activity_id = history_activity_id;
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
}
