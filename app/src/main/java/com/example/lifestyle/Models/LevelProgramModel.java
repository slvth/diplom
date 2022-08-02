package com.example.lifestyle.Models;

import java.io.Serializable;

public class LevelProgramModel implements Serializable {

    int level_program_id;
    int level_id;
    int program_id;


    public LevelProgramModel(int level_program_id, int level_id, int program_id) {
        this.level_program_id = level_program_id;
        this.level_id = level_id;
        this.program_id = program_id;
    }

    public int getLevel_program_id() {
        return level_program_id;
    }

    public void setLevel_program_id(int level_program_id) {
        this.level_program_id = level_program_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }
}
