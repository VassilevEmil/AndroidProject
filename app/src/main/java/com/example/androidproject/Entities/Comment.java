package com.example.androidproject.Entities;

import com.google.firebase.firestore.auth.User;

public class Comment {

    public String id, body, newsId;
    User writtenBy;


    public Comment(String id, String body, String newsId, User writtenBy) {
        this.id = id;
        this.body = body;
        this.newsId = newsId;
        this.writtenBy = writtenBy;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getNewsId() {
        return newsId;
    }

    public User getWrittenBy() {
        return writtenBy;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setWrittenBy(User writtenBy) {
        this.writtenBy = writtenBy;
    }
}
