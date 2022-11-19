package com.example.androidproject.DAO.News;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.NewsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDao implements INewsDao {

    MutableLiveData<List<NewsModel>> newsModel;
    private static NewsDao instance;
    private static final String collectionPath = "news";
    private FirebaseFirestore firestore;

    private NewsDao() {
        newsModel = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
    }

    public static NewsDao getInstance() {
        if (instance == null) {
            instance = new NewsDao();
        }
        return instance;
    }

    @Override
    public void addNews(List<NewsModel> newsModel) {
        Map<String, Object> model = new HashMap<>();
         System.out.println(newsModel);
        System.out.println("eeeeeeeeeeeeeeeeeeeee");

        for(NewsModel item:newsModel){
            firestore.collection(collectionPath).document().set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "news are added successfully to the firebase");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Error with uploading to firebase", e);
                        }
                    });
        }
    }

    @Override
    public void updateNews(NewsModel newNews) {

    }

    public MutableLiveData<List<NewsModel>> getNews(){

        firestore.collection(collectionPath).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    ArrayList<NewsModel> local = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Log.d(TAG, document.getId() + "......" + document.getData());
                        NewsModel temp = document.toObject(NewsModel.class);
                        local.add(temp);
                        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

                    }
                    newsModel.postValue(local);
                }else{
                    Log.d(TAG, "Error getting news", task.getException());
                }
            }
        });
         return  newsModel;
    }

    @Override
    public MutableLiveData<List<NewsModel>> retrunMutableNewsList() {
        return newsModel;
    }
}
