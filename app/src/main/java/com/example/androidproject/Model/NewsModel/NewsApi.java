package com.example.androidproject.Model.NewsModel;

import com.example.androidproject.UI.Responses.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    // search news

    @GET
    Call<NewsResponse> searchNews(
            @Query("apikey")String apiKey,
            @Query("q") String category,
            @Query("language")String language
    );
}
