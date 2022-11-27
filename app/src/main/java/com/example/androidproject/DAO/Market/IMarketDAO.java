package com.example.androidproject.DAO.Market;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Entities.Market.SparklineIn7d;

import java.util.List;

public interface IMarketDAO {
    void addMarkets(List<Market> newsModel);
    MutableLiveData<List<Market>> getMarkets();
    MutableLiveData<List<Double>> getSparkline(String cryptoSymbol);
}
