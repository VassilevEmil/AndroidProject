package com.example.androidproject.DAO.News;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.News.Likes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class LikesDao implements ILikesDao {

    MutableLiveData<List<Likes>> likesModel;
    private static LikesDao instance;
    private static final String collectionPath = "likes";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private LikesDao(){
        likesModel = new MutableLiveData<>();
    }

    public static LikesDao getInstance(){
        if (instance == null){
            instance = new LikesDao();
        }
        return instance;
    }


    @Override
    public void addLike(String userUid, Likes like) {

        if (userUid == null) return;

        Likes likes = new Likes();
        likes.setUserId(userUid);

        myRef.child(collectionPath).child(userUid).setValue(likes);

    }

    @Override
    public void removeLike(Likes like) {

    }

    @Override
    public MutableLiveData<List<Likes>> getLikes(String uid) {
        return null;
    }

    @Override
    public MutableLiveData<List<Likes>> getLikesModal() {
        return null;
    }
}
