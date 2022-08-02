package com.example.lifestyle.UI.Entrance.SignInScreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.HistoryProgramModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.UI.MainScreen;
import com.example.lifestyle.UI.Nutrition.TextValidator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignInScreen extends AppCompatActivity {

    TextInputLayout emailET, passwordET;
    EditText emailEdt, passwordEdt;
    String emailSTR, passwordSTR;
    ImageView back;
    Button create;
    TextView reset;

    //Салават
    DatabaseHelper databaseHelper;
    UserModel userDB;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    public DatabaseReference rootDatabaseRef;

    public static String PrefsSignIn = "prefs";
    SharedPreferences skip_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);
        //Салават
        databaseHelper = DatabaseHelper.getInstance(this);


        passwordEdt = findViewById(R.id.passwordEdt);
        passwordET = findViewById(R.id.passwordET);

        skip_sign_in = getSharedPreferences(SignInScreen.PrefsSignIn, 0);

        emailEdt = findViewById(R.id.emailEdt);
        emailET = findViewById(R.id.emailET);
        emailSTR = emailEdt.getText().toString();
        passwordSTR = passwordEdt.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        create = findViewById(R.id.create);
        create.setOnClickListener(view -> {
            LoginUser();
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());

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

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(view -> {
            startActivity(new Intent(this, ResetPasswordScreen.class));
            passwordET.setError(null);
            emailET.setError(null);
        });
    }

    private void LoginUser() {
        passwordEdt = findViewById(R.id.passwordEdt);
        passwordET = findViewById(R.id.passwordET);

        emailEdt = findViewById(R.id.emailEdt);
        emailET = findViewById(R.id.emailET);
        emailSTR = emailEdt.getText().toString();
        passwordSTR = passwordEdt.getText().toString();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Процесс авторизации");


        if (emailSTR.isEmpty()
                || !emailSTR.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
                || passwordSTR.isEmpty()
                || passwordSTR.length() < 6) {
            if (emailSTR.isEmpty()) {
                emailET.setError("Email не должен быть пустым");
            } else if (!emailSTR.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                emailET.setError("Введите корректный адрес");
            }
            if (passwordSTR.isEmpty()) {
                passwordET.setError("Пароль не должен быть пустым");
            } else if (passwordSTR.length() < 6) {
                passwordET.setError("Пароль не менее 6 символов");
            }
        } else {
            progressDialog.show();
            int k = 0;
            mAuth.signInWithEmailAndPassword(emailSTR,passwordSTR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        //Салават
                        FirebaseUser firebaseUser = task.getResult().getUser();
                        String _uid = firebaseUser != null ? firebaseUser.getUid() : null;
                        userDB = databaseHelper.getUserOnDB(_uid);
                        if(userDB == null && _uid!=null){
                            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                            DocumentReference documentUser = firebaseFirestore.collection("users").document(firebaseUser.getUid());
                            documentUser.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(task.isSuccessful()){
                                        DocumentSnapshot snapshot = task.getResult();
                                        if(snapshot != null){
                                            userDB = snapshot.toObject(UserModel.class);
                                            assert userDB != null;
                                            userDB.setProgram_id(1);
                                            if(databaseHelper.User1_isDefault()){
                                                databaseHelper.updateUser_ID1(userDB, firebaseUser.getUid());
                                                Toast.makeText(getBaseContext(),"Пользователь обновлен USER_ID = 1", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                                startOnMainScreen();
                                            }
                                            else{
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.add(Calendar.HOUR, 3);
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                                String currentTime = dateFormat.format(calendar.getTime());

                                                databaseHelper.addUser(userDB, firebaseUser.getUid());
                                                UserModel currentUser =new UserModel().getCurrentUser(SignInScreen.this);
                                                HistoryProgramModel historyProgram =
                                                        new HistoryProgramModel(currentUser.getUser_id(), 1, 0, currentTime, "");
                                                databaseHelper.addHistoryProgram(historyProgram);
                                                Toast.makeText(getBaseContext(),"Пользователь добавлен в БД", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                                startOnMainScreen();
                                            }
                                        }
                                    }
                                    else{
                                        progressDialog.dismiss();
                                        Log.d("TAG","Error get user",task.getException());
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Log.d("TAG",e.getMessage());
                                }
                            });
                        }
                        else {
                            Toast.makeText(getBaseContext(),"Пользователь существует", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startOnMainScreen();
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Error!", e.getMessage());
                }
            });
        }
    }

    private void startOnMainScreen(){
        SharedPreferences.Editor skip_editor = skip_sign_in.edit();
        skip_editor.putBoolean("hasLoggedIn3", true).apply();
        startActivity(new Intent(SignInScreen.this, MainScreen.class));
        Toast.makeText(SignInScreen.this, "Успешная авторизация", Toast.LENGTH_SHORT).show();
        finishAffinity();
    }

}