package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.google.android.material.textfield.TextInputLayout;

public class SixthPage extends Fragment {

    private static SixthPage instance;
    EditText current_weight_et;
    String current_weightSTR;
    TextInputLayout current_weightET;
    int current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.sixth_page, container, false);

        current_weightET = v.findViewById(R.id.current_weightET);
        current_weight_et = v.findViewById(R.id.current_weight_et);

        return v;
    }

    public static SixthPage GetInstance() {
        return instance;
    }
}