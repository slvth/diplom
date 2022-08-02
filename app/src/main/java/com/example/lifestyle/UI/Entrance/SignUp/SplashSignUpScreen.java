package com.example.lifestyle.UI.Entrance.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;


public class SplashSignUpScreen extends AppCompatActivity {
    Handler h, h1, h2, h3;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_sign_up_screen);

        h = new Handler();
        h1 = new Handler();
        h2 = new Handler();
        h3 = new Handler();
        textView = findViewById(R.id.text_sign_up);

        h2.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Подбираем персональные рекомендации...");
            }
        }, 1500);
        h3.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Рассчитываем макронутриенты...");
            }
        }, 3000);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashSignUpScreen.this, FirstScreen.class);
                startActivity(mainIntent);
                finishAffinity();
            }
        }, 1000);

    }
}