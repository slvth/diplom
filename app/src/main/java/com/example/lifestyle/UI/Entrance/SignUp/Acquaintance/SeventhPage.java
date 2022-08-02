package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.google.android.material.textfield.TextInputLayout;

public class SeventhPage extends Fragment {

    private static SeventhPage instance;
    EditText desired_weight_et;
    String desired_weightSTR;
    int desired;
    TextInputLayout desired_heightET;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.seventh_page, container, false);

        desired_heightET = v.findViewById(R.id.desired_weightET);
        desired_weight_et = v.findViewById(R.id.desired_weight_et);


        return v;
    }

    public static SeventhPage GetInstance() {
        return instance;
    }
}