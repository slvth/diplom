package com.example.lifestyle.UI.Workout.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifestile.R;
import com.example.lifestyle.Database.DatabaseHelper;
import com.example.lifestyle.Models.LevelModel;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.UI.MainScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AllProgramsRVAdapter extends RecyclerView.Adapter<AllProgramsRVAdapter.ViewHolderA> {

    private Context mContext;
    private ArrayList<LevelModel> levels;

    public AllProgramsRVAdapter(Context mContext, ArrayList<LevelModel> levels) {
        this.mContext = mContext;
        this.levels = levels;
    }

    @NonNull
    @Override
    public ViewHolderA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_all_programs, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderA holder, int position) {
        holder.txtLevelName.setText(levels.get(position).getName());
        holder.txtLevel.setText("Уровень "+levels.get(position).getLevel());
        holder.imgLevel.setImageResource(levels.get(position).getImage());

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mContext);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        UserModel user = databaseHelper.getUserOnDB(firebaseUser.getUid());
        ProgramModel currentProgram = databaseHelper.getProgramUser(user.getUser_id());
        int currentLevel = databaseHelper.getLevelViaProgram(currentProgram.getProgram_id());
        if(levels.get(position).getLevel_id() == currentLevel)
            holder.txtInProcess.setVisibility(View.VISIBLE);
        else
            holder.txtInProcess.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class ViewHolderA extends RecyclerView.ViewHolder {

        TextView txtLevelName, txtLevel, txtInProcess;
        ImageView imgLevel;

        public ViewHolderA(@NonNull View itemView) {
            super(itemView);

            txtLevel = itemView.findViewById(R.id.txtLevelAllPrograms);
            txtLevelName = itemView.findViewById(R.id.txtNameAllPrograms);
            txtInProcess = itemView.findViewById(R.id.txtInProgressAllPrograms);
            imgLevel = itemView.findViewById(R.id.imgAllPrograms);


            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mContext);
            ArrayList<LevelModel>  levelList = databaseHelper.getAllLevels();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainScreen) mContext).openLevelDetail(levelList.get(getAdapterPosition()));
                }
            });
        }
    }
}


