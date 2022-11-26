package com.example.androidproject.DAO.Market;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Entities.Market.SparklineIn7d;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MarketDAO implements IMarketDAO{

    MutableLiveData<List<Market>> marketModel;
    MutableLiveData<List<Double>> sparklineModel;
    private static MarketDAO instance;
    private static final String collectionPath = "Markets";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    private MarketDAO() {
        marketModel = new MutableLiveData<>();
        sparklineModel = new MutableLiveData<>();
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
           // myRef.child("Crypto").removeValue();
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

    @Override
    public MutableLiveData<List<Double>> getSparkline() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Double> local_ = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.child("Crypto").child("1").child("sparklineIn7d").child("price").getChildren()){
                    local_.add(dataSnapshot.getValue(Double.class));
                }
                sparklineModel.postValue(local_);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return sparklineModel;
    }

}
