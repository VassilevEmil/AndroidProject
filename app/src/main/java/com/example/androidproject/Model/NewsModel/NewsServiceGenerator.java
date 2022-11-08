package com.example.androidproject.Model.NewsModel;

import com.example.androidproject.Model.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(Credentials.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static NewsApi newsApi = retrofit.create(NewsApi.class);

    public static NewsApi getNewsApi(){
        return newsApi;
    }
}
