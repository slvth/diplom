package com.example.lifestyle.UI.Nutrition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Entrance.EnterScreen;
import com.example.lifestyle.UI.Entrance.SignInScreen.SignInScreen;
import com.example.lifestyle.UI.Entrance.SignUp.Acquaintance.AcquaintanceScreen;
import com.example.lifestyle.UI.Entrance.SignUp.SignUpScreen;
import com.example.lifestyle.UI.MainScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    Handler h;

    SharedPreferences sharedPreferences_up, sharedPreferences_acquaintance, sharedPreferences_in;

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
        setContentView(R.layout.splash_screen);

        sharedPreferences_up = getSharedPreferences(SignUpScreen.PrefsSignUp, 0);
        sharedPreferences_in = getSharedPreferences(SignInScreen.PrefsSignIn, 0);
        sharedPreferences_acquaintance = getSharedPreferences(AcquaintanceScreen.PrefsAcquaintance, 0);
        h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if(firebaseUser!=null){
                    startActivity(new Intent(SplashScreen.this, MainScreen.class));
                }
                else{
                    startActivity(new Intent(SplashScreen.this, EnterScreen.class));
                }

                /*
                if (sharedPreferences_up.getBoolean("hasLoggedIn", false) == true) {
                    if (sharedPreferences_acquaintance.getBoolean("hasLoggedIn2", false) == true) {
                        startActivity(new Intent(SplashScreen.this, MainScreen.class));
                    } else {
                        startActivity(new Intent(SplashScreen.this, AcquaintanceScreen.class));
                    }
                } else if (sharedPreferences_in.getBoolean("hasLoggedIn3", false) == true) {
                    startActivity(new Intent(SplashScreen.this, MainScreen.class));
                } else {
                    startActivity(new Intent(SplashScreen.this, EnterScreen.class));
                }*/
                finishAffinity();
            }
        }, 1000);
    }
}