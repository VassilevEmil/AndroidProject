package com.example.androidproject.DAO.News;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.News.Likes;
import com.example.androidproject.Entities.Wallet.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LikesDao implements ILikesDao{


    MutableLiveData<List<Likes>> likesModel;
    private static LikesDao instance;
    private static final String collectionPath = "likes";

    FirebaseFirestore firestore;


    private LikesDao(){
        likesModel = new MutableLiveData<>();
        firestore=FirebaseFirestore.getInstance();
    }

    public static LikesDao getInstance(){
        if (instance == null){
            instance = new LikesDao();
        }
        return instance;
    }

    @Override
    public void addLike(Likes like) {

        System.out.println(like.toString());

        if (like == null) return;



        firestore.collection(collectionPath).document(like.getUid()).set(like).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Like created successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing the like", e);
                    }
                });

    }

    @Override
    public void removeLike(Likes like) {

    }

    @Override
    public MutableLiveData<List<Likes>> getLikes(String newsRef) {
        firestore.collection(collectionPath).whereEqualTo("newsRef",newsRef).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Likes> local = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Likes localtr = document.toObject(Likes.class);
                        local.add(localtr);
                    }

                   likesModel.postValue(local);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        return likesModel;
    }

    @Override
    public MutableLiveData<List<Likes>> getLikesModal() {
        return likesModel;
    }

}
