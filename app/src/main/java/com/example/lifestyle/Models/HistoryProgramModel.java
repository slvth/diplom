package com.example.lifestyle.Models;

import java.io.Serializable;

public class HistoryProgramModel implements Serializable {

    int history_program_id;
    int user_id;
    int program_id;
    int status;
    String dateStart;
    String dateEnd;

    public HistoryProgramModel() {
    }

    public HistoryProgramModel(int history_program_id, int user_id, int program_id, int status, String dateStart, String dateEnd) {
        this.history_program_id = history_program_id;
        this.user_id = user_id;
        this.program_id = program_id;
        this.status = status;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public HistoryProgramModel(int user_id, int program_id, int status, String dateStart, String dateEnd) {
        this.user_id = user_id;
        this.program_id = program_id;
        this.status = status;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getHistory_program_id() {
        return history_program_id;
    }

    public void setHistory_program_id(int history_program_id) {
        this.history_program_id = history_program_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
