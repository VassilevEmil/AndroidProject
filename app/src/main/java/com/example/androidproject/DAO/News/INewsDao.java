package com.example.androidproject.DAO.News;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.News.NewsModel;

import java.util.List;

public interface INewsDao {
    void addNews(List<NewsModel> newsModel);
    void updateNews(NewsModel newNews);
    MutableLiveData<List<NewsModel>> getNews();

    MutableLiveData<List<NewsModel>> retrunMutableNewsList();
}
