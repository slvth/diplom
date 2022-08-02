package com.example.lifestyle.UI.Workout.WorkoutUI.MyProgram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.LevelModel;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.UI.Workout.WorkoutUI.AllPrograms.ProgramOverviewActivity;

import java.util.ArrayList;

public class SplashScreenNextProgram extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView txtProgramName;
    Button btnNextProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_next_program);

        databaseHelper = DatabaseHelper.getInstance(this);
        txtProgramName = findViewById(R.id.txtProgramNameNextProgram);
        btnNextProgram = findViewById(R.id.btnNextProgram);

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            ProgramModel currentProgram = (ProgramModel) arguments.getSerializable(ProgramModel.class.getSimpleName());
            String programName = currentProgram.getProgramName(SplashScreenNextProgram.this);
            txtProgramName.setText(programName);
            btnNextProgram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ArrayList<LevelModel> levels = databaseHelper.getAllLevels();
                    ArrayList<ProgramModel> programs = databaseHelper.getAllPrograms();
                    ProgramModel nextProgram = new ProgramModel();

                    for (ProgramModel program: programs) {
                        if(program.getProgram_id() == currentProgram.getProgram_id()){
                            int current_index = programs.indexOf(program);
                            if(programs.size()-1>current_index){
                                nextProgram = programs.get(current_index+1);
                                Intent intent = new Intent(SplashScreenNextProgram.this, ProgramOverviewActivity.class);
                                intent.putExtra(ProgramModel.class.getSimpleName(),nextProgram);
                                startActivityForResult(intent, 3);
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3 && resultCode== Activity.RESULT_OK){
            setResult(RESULT_OK,getIntent());
            Toast.makeText(this, "PROGRAM_overview returned ok", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            finish();
        }
    }
}