package com.example.lifestyle.Models;

import java.io.Serializable;

public class WorkoutExerciseModel implements Serializable {
    private int workout_exercise_id;
    private int workout_id;
    private int exercise_id;

    public WorkoutExerciseModel(int workout_id, int exercise_id) {
        this.workout_id = workout_id;
        this.exercise_id = exercise_id;
    }

    public WorkoutExerciseModel(int workout_exercise_id, int workout_id, int exercise_id) {
        this.workout_exercise_id = workout_exercise_id;
        this.workout_id = workout_id;
        this.exercise_id = exercise_id;
    }

    public int getWorkout_exercise_id() {
        return workout_exercise_id;
    }

    public void setWorkout_exercise_id(int workout_exercise_id) {
        this.workout_exercise_id = workout_exercise_id;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }
}
