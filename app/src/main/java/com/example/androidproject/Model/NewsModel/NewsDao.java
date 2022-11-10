package com.example.androidproject.Model.NewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsDao {

    private final MutableLiveData<List<NewsModel>> newsModel;
    private static NewsDao instance;

    private NewsDao(){
        newsModel = new MutableLiveData<>();
        List<NewsModel> newsList = new ArrayList<>();
        newsModel.setValue(newsList);
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
}
