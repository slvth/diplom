package com.example.lifestyle.UI.Entrance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Entrance.SignInScreen.SignInScreen;
import com.example.lifestyle.UI.Entrance.SignUp.SignUpScreen;

public class EnterScreen extends AppCompatActivity {

    RelativeLayout third;
    TextView sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_screen);

        third = findViewById(R.id.third);
        sign_in = findViewById(R.id.sign_in);

        third.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUpScreen.class));
        });
        sign_in.setOnClickListener(view -> {
            startActivity(new Intent(this, SignInScreen.class));
        });
    }
}