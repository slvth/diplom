package com.example.lifestyle.UI.Article;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifestile.R;

public class DetailArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.splash_screen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        TextView txt_HeaderArticleDetail= this.findViewById(R.id.txtHeaderArticleDetail);
        TextView txt_DesArticleDetail= this.findViewById(R.id.txtDesArticleDetail);
        ImageView imageView_ArticleDetail= this.findViewById(R.id.imageViewArticleDetail);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            txt_HeaderArticleDetail.setText(extras.getString("Header"));
            imageView_ArticleDetail.setImageResource(extras.getInt("Image"));
            txt_DesArticleDetail.setText(extras.getString("Description"));
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    public void GoBackDetailArticle(View view) {
        onBackPressed();
    }

}