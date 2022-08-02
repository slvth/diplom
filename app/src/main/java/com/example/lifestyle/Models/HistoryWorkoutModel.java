package com.example.lifestyle.Models;

import java.io.Serializable;

public class HistoryWorkoutModel implements Serializable {
    private int history_workout_id;
    private int user_id;
    private int history_program_id;
    private int workout_id;
    private int time;
    private int calorie;
    private String datetime;
    private String workout_name;
    private int count_exercise;

    public HistoryWorkoutModel() {}

    public HistoryWorkoutModel(int user_id, int history_program_id, int workout_id, int time, int calorie, String date) {
        this.user_id = user_id;
        this.history_program_id = history_program_id;
        this.workout_id = workout_id;
        this.time = time;
        this.calorie = calorie;
        this.datetime = date;
    }

    public HistoryWorkoutModel(int history_workout_id, int user_id, int history_program_id, int workout_id,
                               int time, int calorie, String datetime) {
        this.history_workout_id = history_workout_id;
        this.user_id = user_id;
        this.history_program_id = history_program_id;
        this.workout_id = workout_id;
        this.time = time;
        this.calorie = calorie;
        this.datetime = datetime;
    }



    public int getHistory_workout_id() {
        return history_workout_id;
    }

    public void setHistory_workout_id(int history_workout_id) {
        this.history_workout_id = history_workout_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
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

    public String getDateTime() {
        return datetime;
    }

    public void setDateTime(String date) {
        this.datetime = date;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public int getCount_exercise() {
        return count_exercise;
    }

    public void setCount_exercise(int count_exercise) {
        this.count_exercise = count_exercise;
    }

    public int getHistory_program_id() {
        return history_program_id;
    }

    public void setHistory_program_id(int history_program_id) {
        this.history_program_id = history_program_id;
    }
}
