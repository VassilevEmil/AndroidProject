package com.example.androidproject.Entities.News;

import java.util.UUID;

public class Likes {
    private String uid;
    private String userId;

    public Likes(){
        uid = UUID.randomUUID().toString();
    }

    public Likes(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "uid='" + uid + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
