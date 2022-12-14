package com.example.androidproject.Model.Market;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.androidproject.DAO.Market.MarketDAO;
import com.example.androidproject.Entities.Market.Market;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketRepository {

    private static MarketRepository instance;
    private MutableLiveData < List < Market >> marketData;
    private MarketDAO marketDAO;

    public MarketRepository() {
        marketData = new MutableLiveData < > ();
        this.marketDAO = MarketDAO.getInstance();
    }

    public MutableLiveData < List < Market >> getMarketData() {
        return marketDAO.getMarkets();
    }

    public MutableLiveData < List < Double >> getSparklineData(String cryptoSymbol) {
        return marketDAO.getSparkline(cryptoSymbol);
    }

    public static synchronized MarketRepository getInstance() {
        if (instance == null)
            instance = new MarketRepository();
        return instance;
    }

    public void loadMarket(String currency, String order, int per_page, String percentageChangeTime,
                           boolean sparkline) {

        MarketServiceGenerator.getMarketApi().readMarketData(currency, order, per_page,
                        percentageChangeTime, sparkline)
                .enqueue(new Callback < List < Market >> () {
                    @Override
                    public void onResponse(Call < List < Market >> call, Response < List < Market >>
                            response) {

                        if (response.isSuccessful()) {
                            marketDAO.addMarkets(response.body());
                            Log.d("SUP", "MARKET DATA: " + marketData);
                        }
                    }
                    @Override
                    public void onFailure(Call < List < Market >> call, Throwable t) {
                        Log.d("mresponse", "Response: fail " + t.getMessage());
                    }
                });
    }
}