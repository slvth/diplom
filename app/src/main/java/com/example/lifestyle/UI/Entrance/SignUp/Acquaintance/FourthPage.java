package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.google.android.material.textfield.TextInputLayout;

public class FourthPage extends Fragment {
    private static FourthPage instance;
    EditText height_et;
    TextInputLayout heightET;
    public String heightSTR;
    int height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.fourth_page, container, false);
        height_et = v.findViewById(R.id.height_et);
        heightET = v.findViewById(R.id.heightET);

        return v;
    }

    public static FourthPage GetInstance() {
        return instance;
    }
}