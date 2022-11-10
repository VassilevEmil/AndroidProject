package com.example.androidproject.Model.NewsModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.Entities.NewsModel;
import com.example.androidproject.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;
    List<NewsModel> modelList;
    NewsRepository newsRepository;

    public NewsAdapter(Context context, List<NewsModel> modelList) {
        this.context = context;
        this.modelList = modelList;

    }


    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_news, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {


        // method for opening a new intent when clicking to see a news

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url", modelList.get(i).getUrl());
                context.startActivity(intent);
            }
        });

        viewHolder.mtime.setText("Published at:-" + modelList.get(i).getPubDate());
        viewHolder.mheading.setText(modelList.get(i).getTitle());
        viewHolder.mcreator.setText(modelList.get(i).getCreator());
        viewHolder.mcontent.setText(modelList.get(i).getContent());
        Glide.with(context).load(modelList.get(i).getImage_url()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading, mcontent, mcreator, mtime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mheading = itemView.findViewById(R.id.maintitle);
            mcontent = itemView.findViewById(R.id.content);
            mcreator = itemView.findViewById(R.id.author);
            mtime = itemView.findViewById(R.id.time);
        }
    }
}
