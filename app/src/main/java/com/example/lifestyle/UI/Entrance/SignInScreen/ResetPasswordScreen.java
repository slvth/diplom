package com.example.lifestyle.UI.Entrance.SignInScreen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.UI.Nutrition.TextValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordScreen extends AppCompatActivity {

    TextInputLayout emailET;
    EditText emailEdt;
    String emailSTR;
    ImageView back;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_screen);
        create = findViewById(R.id.create);

        emailEdt = findViewById(R.id.emailEdt);
        emailET = findViewById(R.id.emailET);

        create.setOnClickListener(view -> {
            emailSTR = emailEdt.getText().toString();
            if (emailSTR.isEmpty()) {
                emailET.setError("Поле не может быть пустым");
            } else if (!emailSTR.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                emailET.setError("Введите корректный адрес");
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(emailSTR)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ResetPasswordScreen.this, "Письмо отправлено на вашу почту", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });

        emailEdt.addTextChangedListener(new TextValidator(emailEdt) {
            @Override
            public void validate(TextView textView, String text) {
                emailSTR = emailEdt.getText().toString();
                if (emailSTR.isEmpty()) {
                    emailET.setError("Поле не может быть пустым");
                } else {
                    emailET.setError(null);
                }
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());
    }
}