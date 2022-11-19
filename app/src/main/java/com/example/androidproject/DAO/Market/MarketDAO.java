package com.example.androidproject.DAO.Market;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MarketDAO implements IMarketDAO{

    MutableLiveData<List<Market>> marketModel = new MutableLiveData<>();
    private static MarketDAO instance;
    private static final String collectionPath = "Markets";
    private FirebaseFirestore firestore;



    private MarketDAO() {
        marketModel = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
    }

    public static MarketDAO getInstance() {
        if (instance == null) {
            instance = new MarketDAO();
        }
        return instance;
    }


    @Override
    public void addMarkets(List<Market> marketModel) {

        for(Market item:marketModel){
            firestore.collection(collectionPath).document().set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "marketData is successfully added to the firebase");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Error uploading MarketData to firebase", e);
                        }
                    });
        }
        firestore.collection("Markets").orderBy("marketCapRank", Query.Direction.ASCENDING);
        Log.d("TAG1", "addMarkets: " + FieldPath.of(collectionPath, "marketCapRank")+ "   ");

    }

    @Override
    public MutableLiveData<List<Market>> getMarkets() {

        firestore.collection(collectionPath).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    ArrayList<Market> local = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()){

                        Log.d(TAG, document.getId() + "......" + document.getData());
                        Market temp = document.toObject(Market.class);
                        local.add(temp);
                    }
                    marketModel.postValue(local);
                }else{
                    Log.d(TAG, "Error getting news", task.getException());
                }
            }
        });
        return marketModel;
    }
}
