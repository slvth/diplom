package com.example.lifestyle.UI.Entrance.SignInScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.UI.MainScreen;

public class ReturnSplashScreen extends AppCompatActivity {

    Handler h;

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
        setContentView(R.layout.return_splash_screen);

        h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(ReturnSplashScreen.this, MainScreen.class);
                startActivity(mainIntent);
                finish();

            }
        }, 1000);
    }
}