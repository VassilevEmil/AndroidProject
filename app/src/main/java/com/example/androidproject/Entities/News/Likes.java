package com.example.androidproject.Entities.News;

import com.google.firebase.firestore.auth.User;

import java.util.Date;

public class Likes {

    public int id;
    public String body;
    public Date dateCreated;
    public User user;

    public Likes(int id, String body, Date dateCreated, User user) {
        this.id = id;
        this.body = body;
        this.dateCreated = dateCreated;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", dateCreated=" + dateCreated +
                ", user=" + user +
                '}';
    }
}
