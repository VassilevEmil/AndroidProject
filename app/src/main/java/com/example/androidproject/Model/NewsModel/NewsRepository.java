package com.example.androidproject.Model.NewsModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.DAO.News.LikesDao;
import com.example.androidproject.DAO.News.NewsDao;
import com.example.androidproject.Entities.News.Likes;
import com.example.androidproject.Entities.News.NewsModel;
import com.example.androidproject.UI.Responses.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {


    private static NewsRepository instance;
    private MutableLiveData<List<NewsModel>> searchedNews;
    private NewsDao newsDao;
    private LikesDao likesDao;
    private MutableLiveData<List<Likes>> searchedLikes;

    public NewsRepository() {
        searchedNews = new MutableLiveData<>();
        this.newsDao = NewsDao.getInstance();
        likesDao = LikesDao.getInstance();
    }

    public MutableLiveData<List<NewsModel>> getSearchedNews(){
        return newsDao.getNews();
    }

    public static synchronized NewsRepository getInstance() {

        if (instance == null){
            instance = new NewsRepository();
        }
        return instance;
    }

    // method for searching the news

    public void searchForNews(String api, String category, String language){
        NewsApi newsApi = NewsServiceGenerator.getNewsApi();
        Call<NewsResponse> call = newsApi.searchNews(api, category, language);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    saveNews(response.body().getNews());
                    System.out.println(response.body().getNews());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.i("Retrofit", "Exception from NewsRepository class... check it");
                System.out.println(t.getMessage());
            }
        });
    }

    // saving the news

    public void saveNews(ArrayList<NewsModel> newsModels){
        newsDao.addNews(newsModels);
        searchedNews = newsDao.getNews();


    }

    public void addLike(Likes like){

        likesDao.addLike(like);

    }

    public MutableLiveData<List<Likes>> getLikes(String newsRef) {
        return likesDao.getLikes(newsRef);
    }
}
