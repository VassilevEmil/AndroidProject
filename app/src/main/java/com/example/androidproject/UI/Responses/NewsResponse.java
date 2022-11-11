package com.example.androidproject.UI.Responses;


    // class for getting crypto news requests in english (News list)
import android.widget.ArrayAdapter;

import com.example.androidproject.Entities.NewsModel;

import java.util.ArrayList;

public class NewsResponse {

    private String status;
    // private String title, creator, description, content, pubDate, image_url, category, url;
    private ArrayList<NewsModel> results;

    public NewsResponse(String status, ArrayList<NewsModel> results) {
        this.status = status;
        this.results = results;
    }

    public ArrayList<NewsModel> getNews() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public void setResults(ArrayList<NewsModel> results) {
        this.results = results;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "status='" + status + '\'' +
                ", results=" + results +
                '}';
    }
}


