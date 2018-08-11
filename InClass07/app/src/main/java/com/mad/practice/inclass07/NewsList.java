package com.mad.practice.inclass07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsList extends AppCompatActivity {

    ArrayList<Article> news;
    static String EACH_NEWS_KEY= "EachNews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        ListView listView = findViewById(R.id.lvNews);


        if(getIntent() != null && getIntent().getExtras() != null ){
            news = (ArrayList<Article>) getIntent().getSerializableExtra(MainActivity.NEWS_KEY);
            Log.d("demo", ""+news);
            ArticleAdapter adapter = new ArticleAdapter(this,R.layout.news_item,news);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("demo", "i'm here"+news);
                    Article art = news.get(position);
                    Intent i = new Intent(NewsList.this,NewsDetails.class);
                    i.putExtra(NewsList.EACH_NEWS_KEY,art);
                    startActivity(i);
                }
            });
        }


    }
}
