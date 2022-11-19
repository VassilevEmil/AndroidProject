package com.example.androidproject.Entities.News;

import com.google.firebase.firestore.auth.User;

import java.util.Date;

public class Comments {

    public int id;
    public String body;
    public Date dateCreated;
    public User user;

    public Comments(String body, Date dateCreated, User user, int id) {
        this.body = body;
        this.dateCreated = dateCreated;
        this.user = user;
        this.id=id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", dateCreated=" + dateCreated +
                ", user=" + user +
                '}';
    }
}
