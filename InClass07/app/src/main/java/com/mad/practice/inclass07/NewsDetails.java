package com.mad.practice.inclass07;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsDetails extends AppCompatActivity {

    Article eachArticle;
    TextView tvTitle;
    TextView tvPublish;
    TextView tvDesc;
    ImageView ivMainImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        tvTitle = findViewById(R.id.tvDTitle);
        tvPublish = findViewById(R.id.tvDPublish);
        tvDesc = findViewById(R.id.tvDDesc);

        ivMainImage = findViewById(R.id.ivDMainImage);


        if(getIntent() != null && getIntent().getExtras() != null ){
            eachArticle = (Article) getIntent().getSerializableExtra(NewsList.EACH_NEWS_KEY);
            tvTitle.setText(eachArticle.getTitle());
            tvPublish.setText(eachArticle.getPublishedAt());
            if(isConnected()){
                Picasso.with(this).load(eachArticle.getUrlToImg()).into(ivMainImage);
            }
            tvDesc.setText(eachArticle.getDescription());
        }
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }
}
