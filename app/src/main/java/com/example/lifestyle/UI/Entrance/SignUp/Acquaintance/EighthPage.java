package com.example.lifestyle.UI.Entrance.SignUp.Acquaintance;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.google.android.material.slider.Slider;

public class EighthPage extends Fragment {
    private static EighthPage instance;
    Slider slider;
    TextView text_slider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.eighth_page, container, false);

        slider = v.findViewById(R.id.slider1);
        text_slider = v.findViewById(R.id.text_slider);

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (slider.getValue() == 2) {
                    text_slider.setText("Я могу пройти 5 лестничных пролетов");
                } else if (slider.getValue() == 1) {
                    text_slider.setText("Я могу пройти 3 лестничных пролетов");
                } else if (slider.getValue() == 0) {
                    text_slider.setText("Я умею завязывать шнурки");
                } else if (slider.getValue() == 3) {
                    text_slider.setText("Я могу пройти 7 лестничных пролетов");
                } else if (slider.getValue() == 4) {
                    text_slider.setText("Я могу пройти 9 лестничных пролетов");
                }
            }
        });
        return v;
    }

    public static EighthPage GetInstance() {
        return instance;
    }
}