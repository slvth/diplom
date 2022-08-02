package com.example.lifestyle.UI.Workout.WorkoutUI.AllPrograms;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.LevelModel;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.UI.Workout.Adapters.LevelDetailAdapter;

import java.util.ArrayList;


public class LevelDetailActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView listView;
    LevelModel level;
    boolean isResultOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.page));
            getWindow().setStatusBarColor(getResources().getColor(R.color.page));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detail);

        isResultOK =false;
        databaseHelper = DatabaseHelper.getInstance(this);
        listView = findViewById(R.id.level_detail_list);
        Toolbar toolbar = findViewById(R.id.toolbarLevelDetail);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {

            level = (LevelModel) arguments.getSerializable(LevelModel.class.getSimpleName());

            toolbar.setTitle(level.getName());
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            toolbar.setNavigationOnClickListener(v -> {
                if(isResultOK)
                    setResult(RESULT_OK);
                finish();
            });

            ArrayList<ProgramModel> programList = level.getPrograms();
            LevelDetailAdapter adapter = new LevelDetailAdapter(this, R.layout.item_level_detail, programList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent intent = new Intent(LevelDetailActivity.this, ProgramOverviewActivity.class);
                intent.putExtra(ProgramModel.class.getSimpleName(), programList.get(i));

                UserModel _user =new UserModel().getCurrentUser(this); //текущий пользователь
                int current_program_id =  programList.get(i).getProgram_id(); //текущая программа в списке
                int user_id = _user.getUser_id(); //id пользователя

                int countWorkouts = databaseHelper.getCountWorkoutsInPrograms(user_id, current_program_id);
                int completeWorkouts = databaseHelper.getCountCompleteWorkouts(user_id, current_program_id);

                if(countWorkouts==completeWorkouts)
                    intent.putExtra("complete", true);
                startActivityForResult(intent, 5);
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5 && resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
            //recreate();
        }

    }
}