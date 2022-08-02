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

public class NinthPage extends Fragment {
    Slider slider;
    TextView text_slider;
    private static NinthPage instance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v = inflater.inflate(R.layout.ninth_page, container, false);


        slider = v.findViewById(R.id.slider2);
        text_slider = v.findViewById(R.id.text_slider);

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (slider.getValue() == 2) {
                    text_slider.setText("Я могу отжаться 10 раз");
                } else if (slider.getValue() == 1) {
                    text_slider.setText("Я могу отжаться 5 раз");
                } else if (slider.getValue() == 0) {
                    text_slider.setText("я не могу отжаться");
                } else if (slider.getValue() == 3) {
                    text_slider.setText("Я могу отжаться 15 раз");
                } else if (slider.getValue() == 4) {
                    text_slider.setText("Я могу отжаться 30 раз");
                }
            }
        });

        return v;
    }

    public static NinthPage GetInstance() {
        return instance;
    }
}