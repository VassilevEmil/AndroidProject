package com.example.androidproject.ViewModel.NewsVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidproject.Model.NewsModel.NewsRepository;
import com.example.androidproject.Entities.NewsModel;

import java.util.List;

public class NewsViewModel extends ViewModel {

    NewsRepository repository;

    public NewsViewModel(){
       repository = NewsRepository.getInstance();
    }

    // view model need to get the livedata from the repository

    public LiveData<List<NewsModel>> getNews(){
        return repository.getSearchedNews();
    }

}
