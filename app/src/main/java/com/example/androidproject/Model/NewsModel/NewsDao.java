package com.example.androidproject.Model.NewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.DAO.News.INewsDao;
import com.example.androidproject.Entities.NewsModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class NewsDao implements INewsDao {

    private final MutableLiveData<List<NewsModel>> newsModel;
    private static NewsDao instance;

    private FirebaseFirestore firebaseFirestore;


    private NewsDao(){
        newsModel = new MutableLiveData<>();
        List<NewsModel> newsList = new ArrayList<>();
        newsModel.setValue(newsList);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public static NewsDao getInstance(){
        if(instance == null){
            instance = new NewsDao();
        }
        return instance;
    }

    public LiveData<List<NewsModel>> getNews(){
        return newsModel;
    }

    @Override
    public void addNews() {


    }
}
