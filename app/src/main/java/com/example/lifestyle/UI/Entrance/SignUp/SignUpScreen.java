package com.example.lifestyle.UI.Entrance.SignUp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.UI.Entrance.SignUp.Acquaintance.AcquaintanceScreen;
import com.example.lifestyle.UI.Nutrition.TextValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity {

    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_sign_up;

    TextInputLayout nameET, emailET, passwordET;
    EditText nameEdt, emailEdt, passwordEdt;
    String nameSTR, emailSTR, passwordSTR;
    ImageView back;
    Button create;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        skip_sign_up = getSharedPreferences(SignUpScreen.PrefsSignUp, 0);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);

        nameEdt = findViewById(R.id.nameEdt);
        emailEdt = findViewById(R.id.emailEdt);
        passwordEdt = findViewById(R.id.passwordEdt);

        back = findViewById(R.id.back);
        create = findViewById(R.id.create);

        create.setOnClickListener(view -> {
            createUser();
        });

        back.setOnClickListener(view -> finish());

        nameEdt.addTextChangedListener(new TextValidator(nameEdt) {
            @Override
            public void validate(TextView textView, String text) {
                nameSTR = nameEdt.getText().toString();
                if (nameSTR.isEmpty()) {
                    nameET.setError("Поле не может быть пустым");
                } else {
                    nameET.setError(null);
                }
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
        passwordEdt.addTextChangedListener(new TextValidator(passwordEdt) {
            @Override
            public void validate(TextView textView, String text) {
                passwordSTR = passwordEdt.getText().toString();
                if (passwordSTR.isEmpty()) {
                    passwordET.setError("Поле не может быть пустым");
                } else {
                    passwordET.setError(null);
                }
            }
        });
    }

    private void createUser() {
        nameSTR = nameEdt.getText().toString();
        emailSTR = emailEdt.getText().toString();
        passwordSTR = passwordEdt.getText().toString();
        if (emailSTR.isEmpty()
                || !emailSTR.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
                || nameSTR.isEmpty()
                || passwordSTR.isEmpty()
                || passwordSTR.length() < 6) {
            if (emailSTR.isEmpty()) {
                emailET.setError("Поле не может быть пустым");
            } else if (!emailSTR.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                emailET.setError("Введите корректный адрес");
            }
            if (nameSTR.isEmpty()) {
                nameET.setError("Поле не может быть пустым");
            }
            if (passwordSTR.isEmpty()) {
                passwordET.setError("Поле не может быть пустым");
            } else if (passwordSTR.length() < 6) {
                passwordET.setError("Пароль не менее 6 символов");
            }
        } else {
            mAuth.createUserWithEmailAndPassword(emailSTR, passwordSTR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getInstance().getCurrentUser();
                        DocumentReference df = fStore.collection("users").document(user.getUid());
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("name", nameSTR);
                        userInfo.put("mail", emailSTR);
                        userInfo.put("password", passwordSTR);
                        df.set(userInfo);


                        SharedPreferences.Editor skip_editor = skip_sign_up.edit();
                        skip_editor.putBoolean("hasLoggedIn", true).apply();
                        startActivity(new Intent(SignUpScreen.this, AcquaintanceScreen.class));
                        finishAffinity();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpScreen.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog_Alert);
                        LayoutInflater inflater = getLayoutInflater();
                        View v = inflater.inflate(R.layout.registration_form, null);

                        final RelativeLayout relativeLayout = v.findViewById(R.id.cancel);
                        builder.setView(v).setCancelable(true);
                        final AlertDialog alertDialog = builder.create();
                        relativeLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }
            });
        }
    }
}