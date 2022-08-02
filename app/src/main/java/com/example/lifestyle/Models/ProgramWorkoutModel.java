package com.example.lifestyle.Models;

import java.io.Serializable;

public class ProgramWorkoutModel implements Serializable {
    int program_workout_id;
    int program_id;
    int workout_id;

    public ProgramWorkoutModel(int program_workout_id, int program_id, int workout_id) {
        this.program_workout_id = program_workout_id;
        this.program_id = program_id;
        this.workout_id = workout_id;
    }

    public int getProgram_workout_id() {
        return program_workout_id;
    }

    public void setProgram_workout_id(int program_workout_id) {
        this.program_workout_id = program_workout_id;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }
}
