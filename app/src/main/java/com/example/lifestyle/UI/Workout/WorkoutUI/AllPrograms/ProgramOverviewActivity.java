package com.example.lifestyle.UI.Workout.WorkoutUI.AllPrograms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.HistoryProgramModel;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProgramOverviewActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button btnJoinProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_overview);

        databaseHelper = DatabaseHelper.getInstance(this);
        btnJoinProgram = findViewById(R.id.btnJoinProgram);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            ProgramModel program = (ProgramModel) arguments.getSerializable(ProgramModel.class.getSimpleName());
            boolean isComplete = arguments.getBoolean("complete", false);


            toolbar.setTitle(program.getProgramName(this));
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            if(!isComplete){
                btnJoinProgram.setText("Подключить");
            }
            else{
                btnJoinProgram.setText("Пройти еще раз");
            }

            btnJoinProgram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserModel user = new UserModel().getCurrentUser(ProgramOverviewActivity.this);
                    databaseHelper.updateProgramUser(user,program);
                    Toast.makeText(ProgramOverviewActivity.this,
                            "Вы успешно сменили программу на "+program.getProgramName(ProgramOverviewActivity.this),
                            Toast.LENGTH_SHORT).show();

                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR, 3);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = dateFormat.format(calendar.getTime());

                    HistoryProgramModel historyProgram = new HistoryProgramModel(user.getUser_id(), program.getProgram_id(),0,currentTime, "");
                    databaseHelper.addHistoryProgram(historyProgram);

                    setResult(RESULT_OK, getIntent());
                    finish();
                }
            });
        }
    }
}