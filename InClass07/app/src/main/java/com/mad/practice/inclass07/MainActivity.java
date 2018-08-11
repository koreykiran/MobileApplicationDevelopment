package com.mad.practice.inclass07;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetArticleAsync.IData  {

    //String [] categories = {"Business","Entertainment","General","health","Science","Sports","Technology"};
    String [] categories ;
    static String NEWS_KEY= "news";
    ProgressDialog progressDialog;
    ArrayList<Article> news;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories = getResources().getStringArray(R.array.categories);
        progressDialog = new ProgressDialog(this);
        news= new ArrayList<Article>();


        ListView listView = findViewById(R.id.lvCategories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
        android.R.id.text1, categories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "You clicked on "+ categories[position], Toast.LENGTH_SHORT).show();
                if(isConnected()){
                    count=0;
                    new GetArticleAsync(MainActivity.this,progressDialog).execute("https://newsapi.org/v2/top-headlines?country=us&category="+categories[position]+"&apiKey=593e863e646143639bc244706c81875f");
                }else{
                    Toast.makeText(MainActivity.this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void handleArticles(ArrayList<Article> articles) {
        //news = articles;

        if(articles.size() > 1){
//            ibPrevBtn.setClickable(true);
//            ibPrevBtn.setEnabled(true);
//            ibNextBtn.setClickable(true);
//            ibNextBtn.setEnabled(true);
//            ibPrevBtn.setVisibility(View.VISIBLE);
//            ibNextBtn.setVisibility(View.VISIBLE);

        }
        if(articles!=null && articles.size()>0){
            Log.i("handleUrls", articles.toString());
            news=articles;
            if(isConnected()){
               // ShowNews(0);
                Intent i = new Intent(MainActivity.this,NewsList.class);
                i.putExtra(MainActivity.NEWS_KEY,news);
                startActivity(i);
            }else{
                Toast.makeText(MainActivity.this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        }else{
            //ivMainImage.setImageResource(android.R.color.transparent);
            Toast.makeText(MainActivity.this, "No News Found", Toast.LENGTH_SHORT).show();
//            ibPrevBtn.setClickable(false);
//            ibPrevBtn.setEnabled(false);
//            ibNextBtn.setClickable(false);
//            ibNextBtn.setEnabled(false);
//            ibPrevBtn.setVisibility(View.GONE);
//            ibNextBtn.setVisibility(View.GONE);
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
