package com.example.androidproject.DAO.News;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.News.Likes;

import java.util.List;

public interface ILikesDao {
    void addLike(String userUid, Likes like);
    void removeLike(Likes like);

    MutableLiveData<List<Likes>> getLikes(String uid);
    MutableLiveData<List<Likes>> getLikesModal();


}
