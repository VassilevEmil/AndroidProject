package com.example.androidproject.Entities.News;

import java.util.UUID;

public class Likes {

    private String uid;
    private String newsRef;

    public Likes(){
        uid = UUID.randomUUID().toString();
        newsRef="";
    }

    public String getUid() {
        return uid;
    }

    public Likes(String newsRef){
        this.newsRef = newsRef;
    }

    public String getNewsRef() {
        return newsRef;
    }

    public void setNewsRef(String newsRef) {
        this.newsRef = newsRef;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "uid='" + uid + '\'' +
                ", newsRef='" + newsRef + '\'' +
                '}';
    }

}
