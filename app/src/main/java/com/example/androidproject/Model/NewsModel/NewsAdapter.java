package com.example.androidproject.Model.NewsModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.Entities.News.Likes;
import com.example.androidproject.Entities.News.NewsModel;
import com.example.androidproject.R;
import com.example.androidproject.ViewModel.NewsVM.NewsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;
    List<NewsModel> modelList = new ArrayList<>();
    NewsRepository newsRepository;
    List<Likes> likes = new ArrayList<>();
    NewsViewModel viewModel;

    public NewsAdapter(Context context, List<NewsModel> modelList, NewsViewModel viewModel) {
        this.context = context;
        this.modelList = modelList;
        this.viewModel = viewModel;
    }
//
//    public NewsAdapter(Context context, List<Likes> likes) {
//    }


    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_news, null, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        viewHolder.mtime.setText("Published at:-" + modelList.get(i).getPubDate());
        viewHolder.mheading.setText(modelList.get(i).getTitle());

        viewHolder.mcreator.setText("By " + modelList.get(i).getCreator().get(0));
        viewHolder.mcontent.setText(modelList.get(i).getContent());
        Glide.with(viewHolder.itemView.getContext()).load(modelList.get(i).getImage_url()).error(R.drawable.ic_no_image).into(viewHolder.imageView);
        viewHolder.displayNews(viewHolder, i);
        viewHolder.addLike(viewHolder, i);

        System.out.println(modelList.get(i).getPubDate().toString());

        viewHolder.displayLikes(viewHolder, i);


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading, mcontent, mcreator, mtime, mauthor;
        CardView cardView;
        ImageView imageView;
        private ImageButton imageButton;
        TextView likesCount;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            mheading = itemView.findViewById(R.id.maintitle);
            mcontent = itemView.findViewById(R.id.content);
            mcreator = itemView.findViewById(R.id.author);
            mtime = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageview);
            imageButton = itemView.findViewById(R.id.like_btn);
            likesCount = itemView.findViewById(R.id.likes_textview);
            mauthor = itemView.findViewById(R.id.author);
        }

        // method for opening a new intent when clicking to see a news

        public void displayNews(ViewHolder viewHolder, int i) {

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, webView.class);
                    intent.putExtra("link", modelList.get(i).getLink());
                    context.startActivity(intent);

                }
            });
        }

        // method to show the 'heart like' button for liking the news

        public void displayLikes(ViewHolder viewHolder, int i) {
            viewModel.getLikes(modelList.get(i).getPubDate()).observeForever(new Observer<List<Likes>>() {
                @Override
                public void onChanged(List<Likes> likes) {
                    viewHolder.likesCount.setText(String.valueOf(likes.size()));

                }
            });
        }

        // like a specific article based on the  unique Id (Pubdate)

        public void addLike(ViewHolder viewHolder, int i) {
            viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Likes local = new Likes();
                    local.setNewsRef(modelList.get(i).getPubDate());
                    viewModel.addLike(local);
                }
            });
        }
    }

}

