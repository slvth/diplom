package com.example.lifestyle.Models;

import java.io.Serializable;

public class HistoryLevelModel implements Serializable {
    int history_level_id;
    int user_id;
    int level_id;
    int status;
    String dateStart;
    String dateEnd;

    public HistoryLevelModel(int history_level_id, int user_id, int level_id, int status, String dateStart, String dateEnd) {
        this.history_level_id = history_level_id;
        this.user_id = user_id;
        this.level_id = level_id;
        this.status = status;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getHistory_level_id() {
        return history_level_id;
    }

    public void setHistory_level_id(int history_level_id) {
        this.history_level_id = history_level_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
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
