package com.example.lifestyle.UI.Workout.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.UserModel;

import java.util.List;

public class LevelDetailAdapter extends ArrayAdapter<ProgramModel> {

    private Context mContext;
    private int mResource;
    private List<ProgramModel> programs;

    public LevelDetailAdapter(@NonNull Context context, int resource, @NonNull List<ProgramModel> programs) {
        super(context, resource, programs);
        this.mContext = context;
        this.mResource = resource;
        this.programs=programs;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mContext);

        ImageView imgProgram = convertView.findViewById(R.id.img_level_detail);
        ImageView imgCheck = convertView.findViewById(R.id.img_level_detail2);
        TextView txtProgramName = convertView.findViewById(R.id.txt_level_detail);
        TextView txtInProgress = convertView.findViewById(R.id.txt_progress_level_detail);

        imgProgram.setImageResource(getItem(position).getImage());
        txtProgramName.setText(getItem(position).getProgramName(mContext));

        UserModel _user =new UserModel().getCurrentUser(mContext); //текущий пользователь
        int current_program_id = getItem(position).getProgram_id(); //текущая программа в списке
        int user_id = _user.getUser_id(); //id пользователя
        int user_program_id = _user.getProgram_id(); //id программы пользователя (в процессе)

        if(current_program_id==user_program_id)
            txtInProgress.setVisibility(View.VISIBLE);
        else
            txtInProgress.setVisibility(View.GONE);

        int countWorkouts = databaseHelper.getCountWorkoutsInPrograms(user_id, current_program_id);
        int completeWorkouts = databaseHelper.getCountCompleteWorkouts(user_id, current_program_id);

        if(countWorkouts==completeWorkouts)
            imgCheck.setVisibility(View.VISIBLE);
        else
            imgCheck.setVisibility(View.INVISIBLE);

        return convertView;
    }
}
