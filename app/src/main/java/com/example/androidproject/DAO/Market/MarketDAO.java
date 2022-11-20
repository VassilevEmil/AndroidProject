package com.example.androidproject.DAO.Market;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Model.Market.MarketAdapter;
import com.example.androidproject.UI.Market.MarketFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    private MarketDAO() {
        marketModel = new MutableLiveData<>();
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
            myRef.child("Crypto").child(item.getMarketCapRank().toString()).setValue(item);
        }
    }

    @Override
    public MutableLiveData<List<Market>> getMarkets() {

            myRef.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             ArrayList<Market> local = new ArrayList<>();
             for (DataSnapshot dataSnapshot : snapshot.child("Crypto").getChildren()){
                 Market market = dataSnapshot.getValue(Market.class);
                 local.add(market);
             }
             marketModel.postValue(local);
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });
    return marketModel;
  }

}
