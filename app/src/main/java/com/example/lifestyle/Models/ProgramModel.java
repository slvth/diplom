package com.example.lifestyle.Models;

import android.content.Context;

import com.example.lifestyle.Database.DatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class ProgramModel implements Serializable {
    int program_id;
    String name;
    int level;
    int countWeeks;
    String desc;
    String imageURL;
    int image;
    ArrayList<WorkoutModel> workouts;

    public ProgramModel() {
    }

    public ProgramModel(int program_id, String name, int level, int countWeeks, String desc, int image) {
        this.program_id = program_id;
        this.name = name;
        this.level = level;
        this.countWeeks = countWeeks;
        this.desc = desc;
        this.image = image;
    }

    public ProgramModel(int program_id, String name, int level, int countWeeks, String desc) {
        this.program_id = program_id;
        this.name = name;
        this.level = level;
        this.countWeeks = countWeeks;
        this.desc = desc;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCountWeeks() {
        return countWeeks;
    }

    public void setCountWeeks(int countWeeks) {
        this.countWeeks = countWeeks;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrlImage() {
        return imageURL;
    }

    public void setUrlImage(String urlImage) {
        this.imageURL = urlImage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<WorkoutModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProgramName(Context context){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        return databaseHelper.getProgramName(getProgram_id());
    }

}
