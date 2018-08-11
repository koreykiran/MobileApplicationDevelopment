package com.mad.practice.inclass07;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kiran on 2/25/2018.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        ViewHolder viewHolder;
        if(convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item ,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.ivNews = convertView.findViewById(R.id.ivNews);
            viewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            viewHolder.tvPublish = convertView.findViewById(R.id.tvpublish);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(parent.getContext()).load(article.getUrlToImg()).into(viewHolder.ivNews);
        viewHolder.tvTitle.setText(article.getTitle());
        viewHolder.tvPublish.setText(article.getPublishedAt());
        return convertView;
    }

    private static class ViewHolder{
        ImageView ivNews;
        TextView tvTitle;
        TextView tvPublish;
    }
}
