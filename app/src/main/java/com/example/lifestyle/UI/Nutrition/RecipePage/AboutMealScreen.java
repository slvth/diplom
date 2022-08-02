package com.example.lifestyle.UI.Nutrition.RecipePage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;
import com.squareup.picasso.Picasso;

public class AboutMealScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.about_meal_screen);

        ImageView back=findViewById(R.id.back);
        TextView title_txt=findViewById(R.id.title_txt);
        TextView kal=findViewById(R.id.kal);
        TextView time=findViewById(R.id.time);
        ImageView myImage = findViewById(R.id.img_meal);
        TextView description1=findViewById(R.id.description1);
        TextView description2=findViewById(R.id.description2);
        TextView description3=findViewById(R.id.description3);
        TextView complexity=findViewById(R.id.complexity);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title_txt.setText(extras.getString("name"));
            kal.setText(extras.getLong("kal")+" ккал");
            time.setText(extras.getLong("time")+" мин");
            description1.setText(extras.getString("description1"));
            description2.setText(extras.getString("description2"));
            description3.setText(extras.getString("description3"));
            complexity.setText(extras.getString("complexity"));

            //надо доделать фотографии
            String img=extras.getString("img_meal");
            Picasso.get().load(img).into(myImage);
        }

        back.setOnClickListener(view -> {
            finish();
        });
    }
}