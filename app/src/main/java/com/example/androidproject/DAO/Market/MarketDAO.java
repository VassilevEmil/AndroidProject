package com.example.androidproject.DAO.Market;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
