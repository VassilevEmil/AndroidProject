package com.example.androidproject.UI.Responses;


    // class for getting crypto news requests in english (News list)
import com.example.androidproject.Entities.NewsModel;

public class NewsResponse {


    private String title, creator, description, content, pubDate, image_url, category, url;

    public NewsModel getNews(){
        return new NewsModel(title, creator, description, content, category, pubDate, image_url, url);
    }

//    @SerializedName("results")
//    @Expose
//   private NewsModel newsModel;
//
//
//
//
//    public NewsModel getNewsModel(){
//        return newsModel;
//    }
//
//
//    @Override
//    public String toString() {
//        return "NewsResponse{" +
//                "newsModel=" + newsModel +
//                '}';
//    }
}
