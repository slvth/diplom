package com.example.lifestyle.UI.Workout.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lifestile.R;
import com.example.lifestyle.Models.ActivityModel;

import java.util.ArrayList;

public class AddActiveFragmentAdapter extends ArrayAdapter<ActivityModel> {
    private final Context mContext;
    private final int mResource;

    public AddActiveFragmentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ActivityModel> activities) {
        super(context, resource, activities);
        this.mContext = context;
        this.mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        ImageView image = convertView.findViewById(R.id.img_add_activity);
        TextView txtName = convertView.findViewById(R.id.txt_add_activity);

        image.setImageResource(getItem(position).getImage());
        txtName.setText(getItem(position).getName());

        return convertView;

    }
}
