package com.example.androidproject.Model.Market;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.UI.Responses.MarketResponse;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class MarketRepository {

    private static MarketRepository instance;
    private final MutableLiveData<List<Market>> marketData;
    
    public MarketRepository(){
        marketData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Market>> getMarketData(){
        return marketData;
    }
    
    public static synchronized MarketRepository getInstance(){
        if(instance == null)
            instance = new MarketRepository();
        return instance;
    }

    public void loadMarket(String currency, String order, int per_page, String percentageChangeTime) {


        MarketServiceGenerator.getMarketApi().readMarketData(currency, order, per_page,percentageChangeTime)
                .enqueue(new Callback<List<Market>>() {
            @Override
            public void onResponse(Call<List<Market>> call, Response<List<Market>> response) {

                if (response.isSuccessful()) {
                    marketData.setValue(response.body());
                    }
            }

            @Override
            public void onFailure(Call<List<Market>> call, Throwable t) {
                //Toast.makeText(binding.getRoot().getContext(), "Error: ", Toast.LENGTH_SHORT).show();
                Log.d("mresponse", "Response: fail " + t.getMessage() );}
        });
    }


}
