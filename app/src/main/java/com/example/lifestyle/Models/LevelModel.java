package com.example.lifestyle.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class LevelModel implements Serializable {
    int level_id;
    String name;
    int level;
    String imageURL;
    int image;
    ArrayList<ProgramModel> programs;

    public LevelModel() {
    }

    public LevelModel(int level_id, String name, int level, int image) {
        this.level_id = level_id;
        this.name = name;
        this.level = level;
        this.image = image;
    }


    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<ProgramModel> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<ProgramModel> programs) {
        this.programs = programs;
    }
}
