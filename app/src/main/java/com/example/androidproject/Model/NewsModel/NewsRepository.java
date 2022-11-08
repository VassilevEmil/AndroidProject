package com.example.androidproject.Model.NewsModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.NewsModel;
import com.example.androidproject.UI.Responses.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {


    private static NewsRepository instance;
    private final MutableLiveData<List<NewsModel>> searchedNews;

    public NewsRepository() {
        searchedNews = new MutableLiveData<>();
    }

    public LiveData<List<NewsModel>> getSearchedNews(){
        return searchedNews;
    }

    public static NewsRepository getInstance() {

        if (instance == null){
            instance = new NewsRepository();
        }
        return instance;
    }

    public void searchForNews(String api, String category, String language){
        NewsApi newsApi = NewsServiceGenerator.getNewsApi();
        Call<NewsResponse> call = newsApi.searchNews(api, category, language);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    searchedNews.setValue((List<NewsModel>) response.body().getNews());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.i("Retrofit", "Exception from NewsRepository class... check it");
            }
        });
    }


}
