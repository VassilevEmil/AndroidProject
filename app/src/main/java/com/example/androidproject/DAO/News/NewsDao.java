package com.example.androidproject.DAO.News;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.NewsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDao implements INewsDao {

    MutableLiveData<List<NewsModel>> newsModel;
    private static NewsDao instance;
    private static final String collectionPath = "news";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    private NewsDao() {
        newsModel = new MutableLiveData<>();

    }

    public static NewsDao getInstance() {
        if (instance == null) {
            instance = new NewsDao();
        }
        return instance;
    }

    @Override
    public void  addNews(List<NewsModel> newsModel) {

        for(NewsModel item:newsModel){
            myRef.child("News").child(item.getPubDate()).setValue(item);
        }
    }

    @Override
    public void updateNews(NewsModel newNews) {
    }

    public MutableLiveData<List<NewsModel>> getNews(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<NewsModel> local = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.child("News").getChildren()){
                    NewsModel news = dataSnapshot.getValue(NewsModel.class);
                    local.add(news);

                }
                newsModel.postValue(local);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return  newsModel;


    }
    @Override
    public MutableLiveData<List<NewsModel>> retrunMutableNewsList() {
        return newsModel;
    }
}
