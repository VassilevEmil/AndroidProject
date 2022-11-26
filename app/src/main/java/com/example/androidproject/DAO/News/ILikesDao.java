package com.example.androidproject.DAO.News;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.News.Likes;

import java.util.List;

public interface ILikesDao {

    void addLike(Likes like);
    void removeLike(Likes like);

    MutableLiveData<List<Likes>> getLikes(String newsRef);
    MutableLiveData<List<Likes>> getLikesModal();

}
