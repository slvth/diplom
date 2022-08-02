package com.example.lifestyle.Models;

import android.content.Context;

import com.example.lifestyle.Database.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable {
    int user_id;
    String name;
    int age;
    int height;
    int weight;
    String email;
    String password;
    String uid_user;
    int program_id;
    int program_level_id;
    ArrayList<HistoryWorkoutModel> historyWorkout;
    ArrayList<HistoryProgramModel> historyProgram;

    public UserModel() {
    }

    public UserModel(int user_id, String name, int age, int height, int weight, String email, String password) {
        this.user_id = user_id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.password = password;
    }

    public UserModel(String name, int age, int height, int weight, String email, String password) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid_user() {
        return uid_user;
    }

    public void setUid_user(String uid_user) {
        this.uid_user = uid_user;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public int getProgram_level_id() {
        return program_level_id;
    }

    public void setProgram_level_id(int program_level_id) {
        this.program_level_id = program_level_id;
    }

    public int getUser_idViaUID(Context context) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        int user_id = databaseHelper.getUserId(firebaseUser.getUid());
        return user_id;
    }

    public UserModel getCurrentUser(Context context) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        UserModel user = databaseHelper.getUserOnDB(firebaseUser.getUid());
        return user;
    }
}
